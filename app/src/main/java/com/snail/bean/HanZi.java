package com.snail.bean;

/**
 * Created by lichengcai on 2016/10/17.
 */

public class HanZi {
    private String zi;
    private String pinyin;
    private String bihua;
    private String jijie;
    private String xiangjie;


    @Override
    public String toString() {
        return "HanZi{" +
                "zi='" + zi + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", bihua='" + bihua + '\'' +
                ", jijie='" + jijie + '\'' +
                ", xiangjie='" + xiangjie + '\'' +
                '}';
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getBihua() {
        return bihua;
    }

    public void setBihua(String bihua) {
        this.bihua = bihua;
    }

    public String getJijie() {
        return jijie;
    }

    public void setJijie(String jijie) {
        this.jijie = jijie;
    }

    public String getXiangjie() {
        return xiangjie;
    }

    public void setXiangjie(String xiangjie) {
        this.xiangjie = xiangjie;
    }
}
