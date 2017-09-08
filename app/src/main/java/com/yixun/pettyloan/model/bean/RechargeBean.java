package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class RechargeBean extends BaseJson<RechargeBean.Data> {
    public static final String CUSTOMER_ID = "customer_id";
    public static final String ORDER_TYPE = "order_type";//0-提现 1-充值 2-商品交易
    public static final String PRICE = "price";//所涉及的交易总金额
    public static final String PRODUCT_ID = "product_id";//若为充值或提现则可不传

    public static class Data {
        private String order_id;

        public String getProductOrderId() {
            return order_id;
        }

        public void setProductOrderId(String orderId) {
            this.order_id = orderId;
        }

    }

}
