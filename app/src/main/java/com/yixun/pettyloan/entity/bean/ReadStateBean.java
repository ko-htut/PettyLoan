package com.yixun.pettyloan.entity.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zongkaili on 2017/8/29.
 */

public class ReadStateBean extends RealmObject {

    @PrimaryKey
    private int id;

    public ReadStateBean() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
