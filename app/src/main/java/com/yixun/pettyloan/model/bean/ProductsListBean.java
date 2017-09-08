package com.yixun.pettyloan.model.bean;

import java.util.List;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class ProductsListBean extends BaseJson<ProductsListBean.Data>{
    public static final String PAGE_NUM = "page_num";
    public static final String PAGE_SIZE = "page_size";
    public static final String PRO_CATEGORY_ID = "pro_category_id";
    public static final String PRO_TYPE = "pro_type";
    public static final String QUERY_TYPE = "query_type";


    public static class Data {
        private int count;
        private List<ProductDetailBean.Data> product_list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
        public List<ProductDetailBean.Data> getProductsList() {
            return product_list;
        }

        public void setProductsList(List<ProductDetailBean.Data> product_list) {
            this.product_list = product_list;
        }
    }
}
