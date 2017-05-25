package com.example.alumna.model;

import android.util.Log;

import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.RegisterModelImpl;
import com.example.alumna.presenter.listener.OnRegisterListener;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by Leebobo on 2017/5/23.
 */

public class RegisterModel implements RegisterModelImpl{
    private String tag="registerModel";
    OnRegisterListener mListener;

   public RegisterModel(OnRegisterListener registerListener){
       mListener=registerListener;
   }

    @Override
    public void register(HashMap<String, Object> params) {
        String url= DataUtils.BASEURL+DataUtils.REGISTER;
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
                Log.i("S",result);
                if (status==1){
                    JsonObject userString = jsonObject.get("user").getAsJsonObject();
                    Gson gson = new Gson();
                    UserBean user = gson.fromJson(userString,UserBean.class);
                    mListener.onRegisterSuccess(user);
                }
                else mListener.onRegisterFailure(status);
            }

            @Override
            public void onFailure(Call call) {
                mListener.onError();
            }
        });
    }
}
