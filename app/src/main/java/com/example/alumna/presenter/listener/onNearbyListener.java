package com.example.alumna.presenter.listener;

import com.example.alumna.bean.NearbyUserBean;

import java.util.List;

/**
 * Created by py on 2017/5/24.
 */

public interface onNearbyListener {
    void onSuccess(List<NearbyUserBean> userList);
    void onFail();
}
