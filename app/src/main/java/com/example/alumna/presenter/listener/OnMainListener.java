package com.example.alumna.presenter.listener;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leebobo on 2017/5/19.
 */

public interface OnMainListener {

    void onFriendSuccess(List<UserBean> list);
    void TopicSuccess(ArrayList<TopicBean> list);
    void UploadSuccess(String url);
    void UploadSuccess();
    void onUserSuccess(UserBean user);
    void onError();
}
