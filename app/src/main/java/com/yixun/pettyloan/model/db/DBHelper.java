package com.yixun.pettyloan.model.db;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface DBHelper {

    void insertNewsId(int id);

    /**
     * 查询 阅读记录
     * @param id
     * @return
     */
    boolean queryNewsId(int id);
}
