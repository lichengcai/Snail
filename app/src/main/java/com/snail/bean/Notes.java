package com.snail.bean;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class Notes {
    private String title;
    private String createTime;
    private String body;

    public Notes(String title, String createTime, String body) {
        this.title = title;
        this.createTime = createTime;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
