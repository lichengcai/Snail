package com.snail.bean;

/**
 * Created by zhangshibiao on 16/10/30.
 */

public class Answer {
    String parentId;
    String desc;
    String creatTime;

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getParentId() {
        return parentId;

    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "parentId='" + parentId + '\'' +
                ", desc='" + desc + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
