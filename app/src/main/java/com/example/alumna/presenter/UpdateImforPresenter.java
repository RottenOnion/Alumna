package com.example.alumna.presenter;


import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.UpdateImforModelImpl;
import com.example.alumna.model.UpdateImforModel;
import com.example.alumna.presenter.Interface.UpdateImforPresenterImpl;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.view.Interface.UpdateImforViewImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UpdateImforPresenter implements UpdateImforPresenterImpl {
    private UpdateImforModelImpl uModel;
    private UpdateImforViewImpl uView;

    public UpdateImforPresenter(UpdateImforViewImpl view) {
        this.uView = view;
        uModel = new UpdateImforModel();
    }

    @Override
    public void loadImfor(int uid) {
        uModel.getUser(uid, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                String status=jsonObject.get("status").getAsString();
                if (status.equals("1")){
                    JsonObject user=jsonObject.get("user").getAsJsonObject();
                    Gson gson=new Gson();
                    UserBean userBean=gson.fromJson(user,UserBean.class);
                    uView.showImfor(userBean);
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }


    @Override
    public void UpdateImfor(UserBean user) {
    }

    @Override
    public void UploadImage(final List<ImageItem> imgs) {
        uModel.UploadImage(imgs, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                Log.i(this.getClass().getName(),result);
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }
}
