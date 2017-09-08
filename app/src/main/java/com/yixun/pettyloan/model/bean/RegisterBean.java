package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class RegisterBean extends BaseJson<RegisterBean.Data> {
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    public static final String SECURITY_CODE = "security_code";
    public static final String INVITATION_CODE = "invitation_code";

    public static class Data {
        private String customer_id;//客户id
        private String customer_number;//客户编号

        public String getCustomerId() {
            return customer_id;
        }

        public void setCustomerId(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getCustomerNumber() {
            return customer_number;
        }

        public void setCustomerNumber(String customer_number) {
            this.customer_number = customer_number;
        }
    }

}
