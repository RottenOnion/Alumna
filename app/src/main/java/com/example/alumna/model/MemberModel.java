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

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class MemberModel implements MemberModelImpl {

    OnMemberListener mListener;

    public MemberModel(OnMemberListener memberListener) {
        this.mListener = memberListener;
    }

    @Override
    public void getUser(int id) {
        Map<String,Object> map = new HashMap<>();
        final String mUrl = DataUtils.BASEURL + DataUtils.MEMBER;

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
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                int status=jsonObject.get("status").getAsInt();
                if (status==1){
                    JsonObject userString = jsonObject.get("userall").getAsJsonObject();
                    Gson gson = new Gson();
                    UserBean userBean = gson.fromJson(userString,UserBean.class);
                    mListener.onUserSuccess(userBean);
                }
            }

            @Override
            public void onFailure(Call call) {
            }
        });

    }

    public void getTopicList(int uid) {

    }

}
