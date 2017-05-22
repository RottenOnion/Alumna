package com.example.alumna.bean;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.ArrayList;

public class TopicBean {
    public static String TYPE_TEXT="1";
    public static String TYPE_IMAGE="2";

    @Id
    private Long id;

    @Property(nameInDb = "TID")
    private int tid;

    @Property(nameInDb="UID")
    private int uid;

    @Property(nameInDb = "USERNAME")
    private String username;

    @Property(nameInDb = "LOCATION")
    private String location;

    @Property(nameInDb = "TYPE")
    private String type;

    @Property(nameInDb ="INFORMATION")
    private String imfor;

    @Property(nameInDb = "TIME")
    private String time;

    @Property(nameInDb = "LIKE_NUMBER")
    private int likeNum;

    @Property(nameInDb = "COMMENT_NUMBER")
    private int commentNum;

    @Property(nameInDb = "HEAD")
    private String head;

    private ArrayList<UserBean>likeList;
    private ArrayList<CommentBean>commentList;
    private String image;

    @Generated(hash = 1961217991)
    public TopicBean() {
    }

    @Generated(hash = 2105103690)
    public TopicBean(Long id, int tid, int uid, String username, String location,
            String type, String imfor, String time, int likeNum, int commentNum,
            String head) {
        this.id = id;
        this.tid = tid;
        this.uid = uid;
        this.username = username;
        this.location = location;
        this.type = type;
        this.imfor = imfor;
        this.time = time;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.head = head;
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

    public String getInfor() {
        return imfor;
    }

    public void setInfor(String infor) {
        this.imfor = infor;
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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<UserBean> getLikeList() {
        return likeList;
    }

    public void setLikeList(ArrayList<UserBean> likeList) {
        this.likeList = likeList;
    }

    public ArrayList<CommentBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<CommentBean> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "TopicBean{" +
                "tid=" + tid +
                ", username='" + username + '\'' +
                ", imfor='" + imfor + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
