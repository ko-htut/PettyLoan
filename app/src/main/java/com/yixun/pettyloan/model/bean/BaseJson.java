package com.yixun.pettyloan.model.bean;

import com.yixun.pettyloan.model.http.api.Apis;

import java.io.Serializable;

/**
 * 服务器固定返回格式，替换泛型即可重用BaseJson
 * Created by zongkaili on 2017/8/29.
 */
public class BaseJson<T> implements Serializable {
    private T data;
    private int status_code;
    private String status;
    private String msg;

    public T getData() {
        return data;
    }

    public int getCode() {
        return status_code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (status.equals(Apis.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
