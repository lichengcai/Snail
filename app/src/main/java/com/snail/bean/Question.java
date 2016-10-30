package com.snail.bean;

/**
 * Created by zhangshibiao on 16/10/30.
 */

public class Question {
    String tag;
    String desc;
    String id;
    String answerIds; // 1,2,3
    String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerIds() {
        return answerIds;
    }

    @Override
    public String toString() {
        return "Question{" +
                "tag='" + tag + '\'' +
                ", desc='" + desc + '\'' +
                ", id='" + id + '\'' +
                ", answerIds='" + answerIds + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public void setAnswerIds(String answerIds) {
        this.answerIds = answerIds;
    }
}
