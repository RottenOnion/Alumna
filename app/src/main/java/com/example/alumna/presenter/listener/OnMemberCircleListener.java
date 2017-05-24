package com.example.alumna.presenter.listener;

import com.example.alumna.bean.TopicBean;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/24.
 */

public interface OnMemberCircleListener {
    void onLoadSuccess(ArrayList<TopicBean> list);
    void onError();
    void onLoadFail();
}
