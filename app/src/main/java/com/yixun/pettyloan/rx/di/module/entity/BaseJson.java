package com.yixun.pettyloan.rx.di.module.entity;

import com.yixun.pettyloan.mvp.api.Api;

import java.io.Serializable;

/**
 * 替换范型即可重用BaseJson
 * Created by zongkaili on 2017/8/29.
 */
public class BaseJson<T> implements Serializable {
    private T data;
    private String code;
    private String msg;

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (code.equals(Api.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
