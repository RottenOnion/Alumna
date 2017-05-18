package com.example.alumna.presenter;

import android.util.Log;

import com.example.alumna.DBHelper.DateBaseHelper;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
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
import java.util.List;

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
    public void loadTopicList(final int uid) {
        mView.showProgressBar();

        ArrayList<TopicBean> topicList=mModel.getTopicList(uid);
        for (TopicBean topic:topicList){
            int tid=topic.getTid();
            if (topic.getLikeNum()>0)topic.setLikeList(mModel.getLikeList(tid));
            if (topic.getCommentNum()>0)topic.setCommentList(mModel.getComment(tid));
        }

        mView.showTopicList(topicList);
        mView.hideProgressBar();
    }

//    @Override
//    public void loadTopicList(int uid) {
//        mModel.getTopicList(uid,new HttpRequestCallback<String>() {
//            @Override
//            public void onStart() {
//                mView.showProgressBar();
//            }
//
//            @Override
//            public void onFinish() {
//                mView.hideProgressBar();
//            }
//
//            @Override
//            public void onResponse(String result) {
//
//                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
//                JsonArray jsonArray=jsonObject.getAsJsonArray("list");
//                Gson gson=new Gson();
//                ArrayList<TopicBean>list=new ArrayList<>();
//                for (JsonElement bean:jsonArray){
//                    TopicBean topic=gson.fromJson(bean,new TypeToken<TopicBean>(){ }.getType());
//                    list.add(topic);
//                }
//                mView.showTopicList(list);
//                onFinish();
//            }
//
//            @Override
//            public void onFailure(Call call) {
//                DateBaseHelper helper=DateBaseHelper.getInstance();
//                List<TopicBean> list=helper.queryAll();
//                mView.showTopicList((ArrayList<TopicBean>)list);
//            }
//        });
//    }


}
