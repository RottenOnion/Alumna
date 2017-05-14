package com.example.alumna.model;

import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.LoginModelImpl;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.google.gson.Gson;

import java.util.Map;


/**
 * Created by Leebobo on 2017/4/26.
 */

public class LoginModel implements LoginModelImpl{
    @Override
    public void Login(final Map<String, Object> params, HttpRequestCallback<String>callback) {
        HttpUtil login=HttpUtil.getInstance();
        String url= DataUtils.BASEURL+DataUtils.LOGININ;
        login.PostRequest(url,params,callback);
    }
}
