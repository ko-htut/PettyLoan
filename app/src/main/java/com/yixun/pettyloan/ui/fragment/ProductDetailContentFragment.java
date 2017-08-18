package com.yixun.pettyloan.ui.fragment;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ProductDetailContentFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.tv_earning_title)
    TextView mTvCalculator;

    public static ProductDetailContentFragment getInstance(String title) {
        ProductDetailContentFragment sf = new ProductDetailContentFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_detail_content;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        configRefresh();
    }

    @Override
    protected void initData() {
    }

    private void configRefresh(){
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus(){
        Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                SystemClock.sleep(1000);
                subscriber.onNext("refresh");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mRefresh.setRefreshing(false);
                    }
                });
    }

    @OnClick({R.id.tv_earning_title,R.id.tv_see_detail,R.id.tv_common_problems})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_earning_title:
                start(ProductDetailCalculatorFragment.getInstance());
                break;
            case R.id.tv_see_detail:
                EventBus.getDefault().post(new StartBrotherEvent(ProductIntroduceFragment.getInstance(mTitle)));
                break;
            case R.id.tv_common_problems:
                EventBus.getDefault().post(new StartBrotherEvent(CommonProblemsFragment.getInstance(mTitle)));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }
}