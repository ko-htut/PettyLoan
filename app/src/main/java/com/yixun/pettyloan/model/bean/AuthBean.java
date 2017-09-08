package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class AuthBean extends BaseJson<AuthBean.Data> {
    public static final String USERNAME = "username";
    public static final String ID_CARD = "id_card";

    public static class Data {
        private CustomerBean.Data Data;

        public CustomerBean.Data getData() {
            return Data;
        }

        public void setData(CustomerBean.Data data) {
            Data = data;
        }

    }

}
