package com.example.alumna.presenter.Interface;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface PublishPresenterImpl {

    /*获取上一个页面的选择的图片*/
    void loadSelectedImage();

    /*发布动态*/
    void publish(String topic);

    /*获取定位*/
    void getLocation();
}
