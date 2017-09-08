package com.yixun.pettyloan.model.bean;

/**
 * Created by zongkaili on 17-9-4.
 */

public class Notice extends BaseJson<Notice>{
    private int is_read;
    private int notice_id;
    private String notice_title;
    private String notice_content;
    private String notice_time;
    private int notice_type;

    public Notice(String title, String content,String time,int id) {
        this.notice_title = title;
        this.notice_content = content;
        this.notice_time = time;
        this.notice_id = id;
    }

    public int getNoticeId() {
        return notice_id;
    }

    public void setNoticeId(int Notice_id) {
        this.notice_id = Notice_id;
    }

    public String getNoticeTitle() {
        return notice_title;
    }

    public void setNoticeTitle(String notice_name) {
        this.notice_title = notice_name;
    }

    public String getNoticeContent() {
        return notice_content;
    }

    public void setNoticeContent(String notice_rate) {
        this.notice_content = notice_rate;
    }

    public String getNoticeTime() {
        return notice_time;
    }

    public void setNoticeTime(String notice_time) {
        this.notice_time = notice_time;
    }

    public int getNoticeType() {
        return notice_type;
    }

    public void setNoticeType(int notice_type) {
        this.notice_type = notice_type;
    }
}
