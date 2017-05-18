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

    //void getTopicList(int uid, HttpRequestCallback callback);

    ArrayList<TopicBean> getTopicList(int uid);

    ArrayList<UserBean> getLikeList(int tid);

    ArrayList<CommentBean> getComment(int tid);

}
