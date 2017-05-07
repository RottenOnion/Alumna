package com.example.alumna.model;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class MainModel implements MainModelImpl {
    @Override
    public String getBackground(int uid) {
        //先查找本地，找到就返回，然后后台访问服务器获取背景图路径
        return null;
    }

    @Override
    public UserBean getImfor(int uid) {
        return null;
    }

    @Override
    public ArrayList<TopicBean> getTopicList(int uid) {
        //发送uid和时间戳给服务器，返回动态列表
        return null;
    }

    @Override
    /*点赞*/
    public void setLike(int uid, int tid) {

    }

    @Override
    /*返回该动态下点赞的人的列表*/
    public ArrayList<UserBean> getLikeList(int tid) {
        return null;
    }

    @Override
    /*评论*/
    public void setComment(int uid, int tid, String comment) {

    }

    @Override
    /*返回该动态下的评论列表*/
    public ArrayList<CommentBean> getComment(int tid) {
        return null;
    }
}
