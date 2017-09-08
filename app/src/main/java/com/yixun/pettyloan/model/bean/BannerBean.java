package com.yixun.pettyloan.model.bean;

import java.util.List;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class BannerBean extends BaseJson<BannerBean.Data>{

    public static class Data {
        private int count;
        private List<Banner> banner_list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Banner> getBannerList() {
            return banner_list;
        }

        public void setBannerList(List<Banner> banner_list) {
            this.banner_list = banner_list;
        }


        public class Banner{
            private int banner_channel;
            private int banner_id;
            private String banner_title;
            private String file_url;
            private int order;

            public int getBannerChannel() {
                return banner_channel;
            }

            public void setBannerChannel(int banner_channel) {
                this.banner_channel = banner_channel;
            }
            public int getBannerId() {
                return banner_id;
            }

            public void setBannerId(int banner_id) {
                this.banner_id = banner_id;
            }

            public String getBannerTitle() {
                return banner_title;
            }

            public void setBannerTitle(String banner_title) {
                this.banner_title = banner_title;
            }

            public String getBannerUrl() {
                return file_url;
            }

            public void setBannerUrl(String file_url) {
                this.file_url = file_url;
            }
            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

        }
    }

}
