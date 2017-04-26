package com.example.alumna.bean;

public class TopicBean {
    private int tid;
    private int uid;
    private String location;
    private String type;
    private String imfor;
    private String time;
    private int likeNum;
    private int commentMum;

    public TopicBean(int id) {
        tid = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImfor() {
        return imfor;
    }

    public void setImfor(String imfor) {
        this.imfor = imfor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCommentMum() {
        return commentMum;
    }

    public void setCommentMum(int commentMum) {
        this.commentMum = commentMum;
    }
}
