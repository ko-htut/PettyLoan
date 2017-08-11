package com.yixun.pettyloan.mode;

/**
 * Created by yaolei on 17-8-10.
 */

public class GoodsMode {
    private String goodsName;
    private String bigRate;
    private String littleRate;

    public GoodsMode(){
    }
    public GoodsMode(String goodsName,String bigRate,String littleRate){
        this.goodsName = goodsName;
        this.bigRate = bigRate;
        this.littleRate = littleRate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBigRate() {
        return bigRate;
    }

    public void setBigRate(String bigRate) {
        this.bigRate = bigRate;
    }

    public String getLittleRate() {
        return littleRate;
    }

    public void setLittleRate(String littleRate) {
        this.littleRate = littleRate;
    }
}
