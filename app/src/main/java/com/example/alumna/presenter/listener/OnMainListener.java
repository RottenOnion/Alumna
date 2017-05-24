package com.example.alumna.presenter.listener;

import com.example.alumna.bean.TopicBean;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/19.
 */

public interface OnMainListener {
    void TopicSuccess(ArrayList<TopicBean> list);
    void UploadSuccess(String url);
    void UploadSuccess();
    void onError();
}
