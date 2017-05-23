package com.example.alumna.presenter.listener;

import com.example.alumna.bean.UserBean;

/**
 * Created by Leebobo on 2017/5/23.
 */

public interface OnRegisterListener {
    void onRegisterSuccess(UserBean user);
    void onRegisterFailure(int status);
    void onError();
}
