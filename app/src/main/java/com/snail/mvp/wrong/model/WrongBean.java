package com.snail.mvp.wrong.model;

/**
 * Created by lichengcai on 2016/10/14.
 */

public class WrongBean {
    private String title;
    private String content;
    private String imgUrl;


    @Override
    public String toString() {
        return "WrongBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
