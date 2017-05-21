package com.example.alumna.model;

import android.util.Log;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.presenter.Interface.OnMainListener;
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
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/4/25.
 */

public class MainModel implements MainModelImpl {

    OnMainListener mListener;
    private ArrayList<UserBean> list = new ArrayList<>();

    public MainModel(OnMainListener mainListener){
        this.mListener=mainListener;
    }
    @Override
    public String getBackground(int uid) {
        //先查找本地，找到就返回，然后后台访问服务器获取背景图路径
        return null;
    }

    @Override
    public UserBean getImfor(int uid) {
        return null;
    }

    @Override
    public void getTopicList(int uid) {
        //发送uid和时间戳给服务器，返回动态列表
        String url=new String(DataUtils.BASEURL+DataUtils.INIT);

        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        params.put("location","123");
        params.put("time","1493177167");

        HttpUtil gettopic=HttpUtil.getInstance();

        gettopic.PostRequest(url, params, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {

                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray = jsonObject.get("list").getAsJsonArray();
                Gson gson = new Gson();
                ArrayList<TopicBean> list = new ArrayList<>();
                for (JsonElement bean : jsonArray) {
                    TopicBean topic = gson.fromJson(bean, new TypeToken<TopicBean>() {
                    }.getType());

                    list.add(topic);
                }
                mListener.TopicSuccess(list);
            }

            @Override
            public void onFailure(Call call) {

            }
        });

    }



    @Override
    /*返回该动态下点赞的人的列表*/
    public void getLikeList(final int tid, final OnLikeListResult listener) {

        String url = new String(DataUtils.BASEURL + DataUtils.GETLIKE);
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);

        HttpUtil getlikelist = HttpUtil.getInstance();
        getlikelist.PostRequest(url, params, new HttpRequestCallback<String>() {
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
                    ArrayList<UserBean> likeList = new ArrayList<>();
                    for (JsonElement bean : jsonArray) {
                        UserBean user = gson.fromJson(bean, new TypeToken<UserBean>() {
                        }.getType());
                        likeList.add(user);
                    }
                    listener.success(likeList);
                }
            }

            @Override
            public void onFailure(Call call) {
                mListener.onError();
            }
        });
    }



    @Override
    /*返回该动态下的评论列表*/
    public void getComment(final int tid,final OnCommentResult listener) {
        String url = new String(DataUtils.BASEURL + DataUtils.GETCOMMENT);

        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);

        HttpUtil getComment = HttpUtil.getInstance();
        getComment.PostRequest(url, params, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                int status=jsonObject.get("status").getAsInt();
                if (status == 1) {
                    JsonArray jsonArray = jsonObject.getAsJsonArray("list");
                    ArrayList<CommentBean> commentList = new ArrayList<>();
                    Gson gson = new Gson();
                    for (JsonElement bean : jsonArray) {
                        CommentBean comment = gson.fromJson(bean, new TypeToken<CommentBean>() {
                        }.getType());
                        commentList.add(comment);
                    }
                    listener.success(commentList);
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }
    public interface OnLikeListResult{
        void success(ArrayList<UserBean> list);
    }

    public interface OnCommentResult{
        void success(ArrayList<CommentBean> list);
    }
}
