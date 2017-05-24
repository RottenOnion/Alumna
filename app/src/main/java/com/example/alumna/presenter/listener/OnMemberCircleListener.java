package com.example.alumna.presenter.listener;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/24.
 */

public interface OnMemberCircleListener {
    void onLoadCircleSuccess(ArrayList<TopicBean> list);
    void onError();
    void onLoadFail();
    void onLoadUserSuccess(UserBean user);
}
