package com.yixun.pettyloan.model.bean;

import java.util.List;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class TradeRecordBean extends BaseJson<TradeRecordBean.Data> {
    public static final String PAGE_NUM = "page_num";
    public static final String PAGE_SIZE = "page_size";
    public static final String TRADE_TYPE = "trade_type";

    public static class Data {
        private int count;
        private List<Record> trade_record_list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Record> getTradeRecords() {
            return trade_record_list;
        }

        public void setTradeRecords(List<Record> trade_record_list) {
            this.trade_record_list = trade_record_list;
        }

        public class Record {
            /**
             * trade_money : 1800
             * trade_type : 0
             * order_id : 31
             * create_time : 2017-09-04 15:04:07
             * modify_time : 2017-09-04 15:01:27
             * customer_id : 14
             * total_money : 101800
             * id : 8
             */

            private int trade_money;
            private int trade_type;
            private int order_id;
            private String record_name;
            private String create_time;
            private String modify_time;
            private int customer_id;
            private int total_money;
            private int id;

            public int getTrade_money() {
                return trade_money;
            }

            public void setTrade_money(int trade_money) {
                this.trade_money = trade_money;
            }

            public int getTrade_type() {
                return trade_type;
            }

            public void setTrade_type(int trade_type) {
                this.trade_type = trade_type;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getRecord_name() {
                return record_name;
            }

            public void setRecord_name(String record_name) {
                this.record_name = record_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getModify_time() {
                return modify_time;
            }

            public void setModify_time(String modify_time) {
                this.modify_time = modify_time;
            }

            public int getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(int customer_id) {
                this.customer_id = customer_id;
            }

            public int getTotal_money() {
                return total_money;
            }

            public void setTotal_money(int total_money) {
                this.total_money = total_money;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

}
