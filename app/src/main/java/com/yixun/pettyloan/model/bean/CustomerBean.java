package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class CustomerBean extends BaseJson<CustomerBean.Data> {
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    public static final String SECURITY_CODE = "security_code";

    public static final String USERNAME = "username";
    public static final String ID_CARD = "id_card";

    public static class Data {
        /**
         * username : lll
         * remark : 444
         * available_credit : 11
         * last_login_time : 2017-09-05 11:38:40
         * customer_level : 0
         * login_count : 9
         * age : 22
         * id_card : 1234567890123
         * register_time : 2017-09-04 10:08:15
         * is_real_name_auth : true
         * sex : 1
         * phone : 15520823648
         * create_time : 2017-09-04 10:23:23
         * modify_time : 2017-09-05 14:29:53
         * total_credit : 12
         * total_assets : 1.002190688E7
         * mail : 123
         * customer_number : 000014
         * available_assets : 121906.99
         * id : 14
         * login_time : 2017-09-05 14:29:53
         */

        private String username;
        private String remark;
        private int available_credit;
        private String last_login_time;
        private int customer_level;
        private int login_count;
        private int age;
        private String id_card;
        private String register_time;
        private boolean is_real_name_auth;//0-未通过实名认证 1-已实名认证
        private int sex;
        private String phone;
        private String create_time;
        private String modify_time;
        private int total_credit;
        private double total_assets;
        private String mail;
        private String customer_number;
        private double available_assets;
        private int id;
        private String login_time;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getAvailable_credit() {
            return available_credit;
        }

        public void setAvailable_credit(int available_credit) {
            this.available_credit = available_credit;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public int getCustomer_level() {
            return customer_level;
        }

        public void setCustomer_level(int customer_level) {
            this.customer_level = customer_level;
        }

        public int getLogin_count() {
            return login_count;
        }

        public void setLogin_count(int login_count) {
            this.login_count = login_count;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public boolean isIs_real_name_auth() {
            return is_real_name_auth;
        }

        public void setIs_real_name_auth(boolean is_real_name_auth) {
            this.is_real_name_auth = is_real_name_auth;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public int getTotal_credit() {
            return total_credit;
        }

        public void setTotal_credit(int total_credit) {
            this.total_credit = total_credit;
        }

        public double getTotal_assets() {
            return total_assets;
        }

        public void setTotal_assets(double total_assets) {
            this.total_assets = total_assets;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getCustomer_number() {
            return customer_number;
        }

        public void setCustomer_number(String customer_number) {
            this.customer_number = customer_number;
        }

        public double getAvailable_assets() {
            return available_assets;
        }

        public void setAvailable_assets(double available_assets) {
            this.available_assets = available_assets;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

    }

}
