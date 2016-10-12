package com.snail.bean;

/**
 * Created by zhangshibiao on 16/10/10.
 */

public class Book {
    private String title;
    private String cover;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public Book(String title, String cover,String info) {
        this.title = title;
        this.cover = cover;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
