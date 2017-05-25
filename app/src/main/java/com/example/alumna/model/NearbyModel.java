package com.example.alumna.model;


import android.util.Log;

import com.example.alumna.bean.NearbyUserBean;
import com.example.alumna.model.Interface.NearbyModelImpl;
import com.example.alumna.presenter.listener.onNearbyListener;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyModel implements NearbyModelImpl {

    private onNearbyListener nearbyListener;


    public NearbyModel(onNearbyListener nearbyListener) {
        this.nearbyListener = nearbyListener;
    }

    @Override
    public void getNearby(int uid,String location) {
        //向服务器请求获取附近的人列表
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("location",location);
        HttpUtil.getInstance().PostRequest(DataUtils.BASEURL + DataUtils.NEARBY, map, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                Log.d("cao","onStart");
            }

            @Override
            public void onFinish() {
                Log.d("cao","onFinish");
            }

            @Override
            public void onResponse(String result) {
                Log.d("cao","拿到附近的校友了");
                Log.d("cao",result);
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                int status = jsonObject.get("status").getAsInt();
                if (status == 1) {
                    JsonArray jsonArray = jsonObject.getAsJsonArray("list");
                    Gson gson = new Gson();
                    List<NearbyUserBean> userList = new ArrayList<>();
                    for (JsonElement user : jsonArray) {
                        NearbyUserBean userBean = gson.fromJson(user, new TypeToken<NearbyUserBean>() {
                        }.getType());
                        userList.add(userBean);
                    }
                    nearbyListener.onSuccess(userList);


                } else {
                    nearbyListener.onFail();
                }
            }

            @Override
            public void onFailure(Call call) {
                Log.d("cao","onFailure");
                nearbyListener.onFail();
            }
        });
    }

    @Override
    public void addFriend(int uid, int fid) {
        //添加好友
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("fid",fid);
        HttpUtil.getInstance().PostRequest(DataUtils.BASEURL + DataUtils.MAKEFRIEND, map,
                new HttpRequestCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onResponse(String result) {
                        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                        int status = jsonObject.get("status").getAsInt();
                        if (status == 1) {

                        }
                    }

                    @Override
                    public void onFailure(Call call) {

                    }
                });
    }
}
