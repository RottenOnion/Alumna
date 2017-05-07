package com.example.alumna.bean;

/**
 * Created by Administrator on 2017/4/25.
 */

public class CommentBean {
    private int cid;
    private int uid;
    private int tid;
    private String comment;
    private String time;

    public CommentBean(int id) {
        this.cid = id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
