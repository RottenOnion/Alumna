package com.example.alumna.view.Interface;


import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainViewImpl {

    void showProgressBar();

    void hideProgressBar();

    void showBackground(String url);

    void showImfor(UserBean user);

    void showTopicList(ArrayList<TopicBean> list);

    void showComment(ArrayList<CommentBean> list);
}
