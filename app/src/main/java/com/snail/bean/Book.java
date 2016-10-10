package com.snail.bean;

/**
 * Created by zhangshibiao on 16/10/10.
 */

public class Book {
    private String title;
    private String cover;
    public Book(String title, String cover) {
        this.title = title;
        this.cover = cover;
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
