package com.snail.bean;

/**
 * Created by zhangshibiao on 16/10/15.
 */

public class Word {
    private String name;
    private String desc;
    private String symbol;
    private String sound;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
    public String toString() {
        return "name:" +this.getName() + " desc:" + this.getDesc();
    }
}
