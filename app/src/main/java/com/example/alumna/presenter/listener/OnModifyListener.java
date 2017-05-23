package com.example.alumna.presenter.listener;

import com.example.alumna.bean.UserBean;

/**
 * Created by Leebobo on 2017/5/22.
 */

public interface OnModifyListener {
    void OnLoadUserSuccess(UserBean user);
    void OnUploadImageSuccess(String url);
    void OnModifyInformSuccess(UserBean user);
    void OnError();
}
