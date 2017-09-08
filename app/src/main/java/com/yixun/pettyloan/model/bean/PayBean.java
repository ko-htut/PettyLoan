package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class PayBean extends BaseJson<PayBean.Data> {
    public static final String CUSTOMER_ID = "customer_id";
    public static final String ORDER_TYPE = "order_type";
    public static final String PRICE = "price";
    public static final String PRODUCT_ID = "product_id";
    public static final String ORDER_STATUS = "order_status";

    public static class Data {
        private String order_id;

        public String getOrderId() {
            return order_id;
        }

        public void setOrderId(String orderid) {
            this.order_id = orderid;
        }
    }

}
