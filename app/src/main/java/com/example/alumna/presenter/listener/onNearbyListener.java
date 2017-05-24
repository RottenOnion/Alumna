package com.example.alumna.presenter.listener;

import com.example.alumna.bean.UserBean;

import java.util.List;

/**
 * Created by py on 2017/5/24.
 */

public interface onNearbyListener {
    void onSuccess(List<UserBean> userList);
    void onFail();
}
