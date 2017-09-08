package com.yixun.pettyloan.model.bean;

import java.util.List;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class NoticesListBean extends BaseJson<NoticesListBean.Data> {
    public static final String NOTICE_STATUS = "notice_status";
    public static final String NOTICE_TYPE = "notice_type";
    public static final String PAGE_NUM = "page_num";
    public static final String PAGE_SIZE = "page_size";


    public static class Data {
        private int count;
        private List<Notice> notice_list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Notice> getNoticeList() {
            return notice_list;
        }

        public void setNoticesList(List<Notice> notice_list) {
            this.notice_list = notice_list;
        }

    }

}
