package com.yixun.pettyloan.rx;

import com.yixun.pettyloan.model.http.ApiException;
import com.yixun.pettyloan.model.http.HttpResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zongkaili on 2017/8/28.
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<HttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<HttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(HttpResponse<T> tHttpResponse) {
                        if(!tHttpResponse.getError()) {
                            return createData(tHttpResponse.getResults());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
//    public static <T> FlowableTransformer<WXHttpResponse<T>, T> handleWXResult() {   //compose判断结果
//        return new FlowableTransformer<WXHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<WXHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<WXHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(WXHttpResponse<T> tWXHttpResponse) {
//                        if(tWXHttpResponse.getCode() == 200) {
//                            return createData(tWXHttpResponse.getNewslist());
//                        } else {
//                            return Flowable.error(new ApiException(tWXHttpResponse.getMsg(), tWXHttpResponse.getCode()));
//                        }
//                    }
//                });
//            }
//        };
//    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleMyResult() {   //compose判断结果
        return new FlowableTransformer<HttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<HttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(HttpResponse<T> tHttpResponse) {
                        if(tHttpResponse.getCode() == 200) {
                            return createData(tHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(tHttpResponse.getMessage(), tHttpResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleGoldResult() {   //compose判断结果
        return new FlowableTransformer<HttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<HttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(HttpResponse<T> tHttpResponse) {
                        if(tHttpResponse.getResults() != null) {
                            return createData(tHttpResponse.getResults());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
