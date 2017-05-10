package com.example.alumna.presenter;

import android.util.Log;

import com.example.alumna.DBHelper.DateBaseHelper;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.model.MainModel;
import com.example.alumna.presenter.Interface.MainPresenterImpl;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.view.Interface.MainViewImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/25.
 */

public class MainPresenter implements MainPresenterImpl {

    private MainViewImpl mView;
    private MainModelImpl mModel;

    public MainPresenter(MainViewImpl view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void loadTopicList(int uid) {
        mModel.getTopicList(uid,new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                mView.showProgressBar();
            }

            @Override
            public void onFinish() {
                mView.hideProgressBar();
            }

            @Override
            public void onResponse(String result) {
                DateBaseHelper helper=DateBaseHelper.getInstance();

                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray=jsonObject.getAsJsonArray("list");
                Gson gson=new Gson();
                ArrayList<TopicBean>list=new ArrayList<>();
                for (JsonElement bean:jsonArray){
                    TopicBean topic=gson.fromJson(bean,new TypeToken<TopicBean>(){ }.getType());
                    list.add(topic);
                    //helper.update(topic);
                }
                //helper.insertAll(list);
                mView.showTopicList(list);
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }


}
