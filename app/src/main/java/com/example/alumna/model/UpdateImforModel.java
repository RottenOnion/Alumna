package com.example.alumna.model;


import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.UpdateImforModelImpl;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UpdateImforModel implements UpdateImforModelImpl {
    @Override
    public void getUser(int uid,HttpRequestCallback<String>callback) {
        HttpUtil getuser=HttpUtil.getInstance();
        String url=DataUtils.BASEURL+DataUtils.GETUSER;
        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        getuser.PostRequest(url,params,callback);
    }

    @Override
    public void setUser(UserBean user,HttpRequestCallback<String>callback) {
    }

    @Override
    public void UploadImage(List<ImageItem> imgs, HttpRequestCallback<String> callback) {
        HttpUtil uploadimg=HttpUtil.getInstance();
        String url=DataUtils.BASEURL+DataUtils.SENDPIC;
        uploadimg.UploadImage(url,imgs,callback);
    }
}
