package com.example.alumna.presenter.Interface;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface PublishPresenterImpl {

    /*上传图片*/
    void publishImageType(final String imfor,final String location);

    /*发布动态*/
    void publish(int type);

    /*获取定位*/
    void getLocation();

}
