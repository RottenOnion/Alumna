package com.example.alumna.model.Interface;

import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface UpdateImforModelImpl {

    /*获取用户信息*/
    void getUser(int uid,HttpRequestCallback<String>callback);

    void setUser(UserBean user,HttpRequestCallback<String>callback);

    void UploadImage(List<ImageItem>imgs,HttpRequestCallback<String> callback);
}
