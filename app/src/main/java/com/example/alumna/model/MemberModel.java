package com.example.alumna.model;


import android.util.Log;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberModelImpl;
import com.example.alumna.presenter.Interface.OnMemberListener;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class MemberModel implements MemberModelImpl {

    OnMemberListener mListener;
    final String mUrl = DataUtils.BASEURL + DataUtils.GETUSER;

    public MemberModel(OnMemberListener memberListener) {
        this.mListener = memberListener;
    }

    @Override
    public UserBean getUser(int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",id);
        HttpUtil.getInstance().PostRequest(mUrl, map, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                Log.d("ccl","onStart");
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                Log.d("ccl","onResponse");
                Log.d("ccl",result);
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                String userString = jsonObject.get("user").getAsString();
                Log.d("ccl","test");
                Gson gson = new Gson();
                //UserBean userBean = gson.fromJson(userString,UserBean.class);
                Log.d("ccl",userString);

            }

            @Override
            public void onFailure(Call call) {
                Log.d("ccl",call.toString());
            }
        });

        return null;
    }

    public ArrayList<TopicBean> getTopicList(int uid) {

        return null;
    }

}
