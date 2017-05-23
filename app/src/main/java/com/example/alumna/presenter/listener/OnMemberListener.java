package com.example.alumna.presenter.listener;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by py on 2017/5/17.
 */

public interface OnMemberListener {
    void onUserSuccess(UserBean user);
    void onTopicSuccess(ArrayList<TopicBean> list);
    void onError();
}
