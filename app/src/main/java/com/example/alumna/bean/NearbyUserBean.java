package com.example.alumna.bean;

/**
 * Created by py on 2017/5/25.
 */

public class NearbyUserBean {
    private int uid;
    private String head;
    private String username;
    private int sex;
    private String phone;
    private String school;
    private String grade;
    private String distance;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "NearbyUserBean{" +
                "uid=" + uid +
                ", head='" + head + '\'' +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
