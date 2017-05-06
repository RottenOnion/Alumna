package com.example.alumna.model.Interface;


import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.Http.HttpRequestCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainModelImpl {
    String getBackground(int uid);

    UserBean getImfor(int uid);

    ArrayList<TopicBean> getTopicList(int uid, HttpRequestCallback callback);

    void setLike(int uid, int tid);

    ArrayList<UserBean> getLikeList(int tid);

    void setComment(int uid, int tid, String comment);

    ArrayList<CommentBean> getComment(int tid);

}
