package com.snail.bean;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class Notes {
    private String title;
    private String createTime;
    private String body;
    private String id;

    public Notes(String title, String createTime, String body, String id) {
        this.title = title;
        this.createTime = createTime;
        this.body = body;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Notes() {


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
