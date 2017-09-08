package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 2017/8/29.
 */
public class ProductDetailBean extends BaseJson<ProductDetailBean.Data> {
    public static final String CUSTOMER_ID = "customer_id";
    public static final String ORDER_TYPE = "order_type";
    public static final String PRICE = "price";
    public static final String PRODUCT_ID = "product_id";
    public static final String ORDER_STATUS = "order_status";

    public static class Data {
        /**
         create_time	创建时间	string	例如1900-01-01 00:00:00
         is_index	是否推荐到首页	number	1-推荐到首页； 0-未推荐；
         min_money	起买金额	number	估计是100的整数倍
         pay_mode	支付方式	number	0-虚拟支付； 1-实物支付
         pro_category_id	商品分类	number	管理员管理的分类
         pro_category_name	商品分类名称	string
         pro_name	商品名称	string
         pro_number	商品编号	number
         pro_status	商品状态	number	1-上架中； 0-下架中；
         pro_type	商品类型	number	0-实物商品； 1-投资商品；
         product_id	商品id	number
         rate	利率	number
         remark	备注	string
         saleroom	saleroom	number
         total_money	募集总金额	number	货币类型人民币，估计是100的整数倍
         trade_end_time	交易结束时间	string	例如1900-01-01 00:00:00
         trade_start_time	交易开始时间	string	例如1900-01-01 00:00:00
         */
        /**
         * pro_number : 1504165681000001
         * pro_status : 1
         * create_user : admin
         * rate : 8
         * create_time : 2017-08-31 15:48:01
         * trade_start_time : 2017-08-31 15:46:00
         * pro_name : 777777
         * pro_category_id : 2
         * pro_type : 1
         * pay_mode : 0
         * min_money : 11111
         * publish_time : 2017-08-31 15:48:13
         * trade_end_time : 2017-10-25 15:46:00
         * total_money : 44444
         * product_id : 9
         * is_index : true
         * remark : DDDDDDDDDDDDDDD
         * pro_category_name : 银行理财
         * modify_time : 2017-08-31 15:49:02
         * modify_user : admin
         * saleroom : 0
         * quantity : 0
         */

        private String pro_number;
        private int pro_status;
        private String create_user;
        private float rate;
        private String create_time;
        private String trade_start_time;
        private String pro_name;
        private int pro_category_id;
        private int pro_type;
        private int pay_mode;
        private float min_money;
        private String publish_time;
        private String trade_end_time;
        private double total_money;
        private int product_id;
        private boolean is_index;
        private String remark;
        private String pro_category_name;
        private String modify_time;
        private String modify_user;
        private double saleroom;
        private int quantity;

        public String getPro_number() {
            return pro_number;
        }

        public void setPro_number(String pro_number) {
            this.pro_number = pro_number;
        }

        public int getPro_status() {
            return pro_status;
        }

        public void setPro_status(int pro_status) {
            this.pro_status = pro_status;
        }

        public String getCreate_user() {
            return create_user;
        }

        public void setCreate_user(String create_user) {
            this.create_user = create_user;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTrade_start_time() {
            return trade_start_time;
        }

        public void setTrade_start_time(String trade_start_time) {
            this.trade_start_time = trade_start_time;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public int getPro_category_id() {
            return pro_category_id;
        }

        public void setPro_category_id(int pro_category_id) {
            this.pro_category_id = pro_category_id;
        }

        public int getPro_type() {
            return pro_type;
        }

        public void setPro_type(int pro_type) {
            this.pro_type = pro_type;
        }

        public int getPay_mode() {
            return pay_mode;
        }

        public void setPay_mode(int pay_mode) {
            this.pay_mode = pay_mode;
        }

        public float getMin_money() {
            return min_money;
        }

        public void setMin_money(float min_money) {
            this.min_money = min_money;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getTrade_end_time() {
            return trade_end_time;
        }

        public void setTrade_end_time(String trade_end_time) {
            this.trade_end_time = trade_end_time;
        }

        public double getTotal_money() {
            return total_money;
        }

        public void setTotal_money(double total_money) {
            this.total_money = total_money;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public boolean isIs_index() {
            return is_index;
        }

        public void setIs_index(boolean is_index) {
            this.is_index = is_index;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPro_category_name() {
            return pro_category_name;
        }

        public void setPro_category_name(String pro_category_name) {
            this.pro_category_name = pro_category_name;
        }

        public String getModify_time() {
            return modify_time;
        }

        public void setModify_time(String modify_time) {
            this.modify_time = modify_time;
        }

        public String getModify_user() {
            return modify_user;
        }

        public void setModify_user(String modify_user) {
            this.modify_user = modify_user;
        }

        public double getSaleroom() {
            return saleroom;
        }

        public void setSaleroom(double saleroom) {
            this.saleroom = saleroom;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

}
