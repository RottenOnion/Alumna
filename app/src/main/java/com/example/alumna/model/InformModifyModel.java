package com.example.alumna.model;


import android.util.Log;

import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.InformModifyModelImpl;
import com.example.alumna.presenter.listener.OnModifyListener;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/25.
 */

public class InformModifyModel implements InformModifyModelImpl {

    OnModifyListener mListener;
    public InformModifyModel(OnModifyListener modifyListener){
        mListener=modifyListener;
    }
    @Override
    public void getUser(final int uid) {
        String url=DataUtils.BASEURL+DataUtils.MEMBER;
        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);

        HttpUtil.getInstance().PostRequest(url, params, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                int status=jsonObject.get("status").getAsInt();
                if (status==1){
                    JsonObject userString = jsonObject.get("userall").getAsJsonObject();
                    Gson gson = new Gson();
                    UserBean user = gson.fromJson(userString,UserBean.class);
                    mListener.OnLoadUserSuccess(user);
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });

    }

    @Override
    public void InformModify(HashMap<String,Object> params) {
        String url=DataUtils.BASEURL+DataUtils.MODIFY;

        HttpUtil.getInstance().PostRequest(url, params, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                int status=jsonObject.get("status").getAsInt();
                if (status==1){
                    JsonObject userString = jsonObject.get("user").getAsJsonObject();
                    Gson gson = new Gson();
                    UserBean user = gson.fromJson(userString,UserBean.class);
                    mListener.OnModifyInformSuccess(user);
                }
            }

            @Override
            public void onFailure(Call call) {
                mListener.OnError();
            }
        });
    }

    @Override
    public void UploadImage(List<ImageItem> imgs) {
        HttpUtil uploadimg=HttpUtil.getInstance();
        String url=DataUtils.BASEURL+DataUtils.SENDPIC;
        uploadimg.UploadImage(url, imgs, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                String imageurl=jsonObject.get("url").getAsString();
                mListener.OnUploadImageSuccess(imageurl);
            }

            @Override
            public void onFailure(Call call) {
                mListener.OnError();
            }
        });
    }
}
