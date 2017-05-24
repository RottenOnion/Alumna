package com.example.alumna.presenter.listener;

/**
 * Created by Leebobo on 2017/5/24.
 */

public interface OnPublishListener {
    void OnImageSuccess(String url);
    void OnPublishSuccess();
    void OnError();
    void OnFailure();
    void OnPublishStart();
}
