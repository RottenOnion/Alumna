package com.example.alumna.model;


import com.example.alumna.bean.UserBean;
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
    private final String mUrl = DataUtils.BASEURL + DataUtils.GETLIKE;

    public NearbyModel(onNearbyListener nearbyListener) {
        this.nearbyListener = nearbyListener;
    }

    @Override
    public void getNearby(int uid,String location) {
        //向服务器请求获取附近的人列表
        Map<String,Object> map = new HashMap<>();
        map.put("tid",uid);
        HttpUtil.getInstance().PostRequest(mUrl, map, new HttpRequestCallback<String>() {
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
                    JsonArray jsonArray = jsonObject.getAsJsonArray("list");
                    Gson gson = new Gson();
                    List<UserBean> userList = new ArrayList<UserBean>();
                    for (JsonElement user : jsonArray) {
                        UserBean userBean = gson.fromJson(user, new TypeToken<UserBean>() {
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
                nearbyListener.onFail();
            }
        });
    }
}
