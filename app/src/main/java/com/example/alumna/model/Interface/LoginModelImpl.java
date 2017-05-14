package com.example.alumna.model.Interface;

import com.example.alumna.utils.Http.HttpRequestCallback;

import java.util.Map;

/**
 * Created by Leebobo on 2017/4/26.
 */

public interface LoginModelImpl {

    void Login(Map<String, Object> params, HttpRequestCallback<String>callback);

}
