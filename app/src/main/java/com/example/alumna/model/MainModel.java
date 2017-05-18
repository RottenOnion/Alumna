package com.example.alumna.model;

import android.util.Log;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;
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
    public ArrayList<TopicBean> getTopicList(int uid) {
        //发送uid和时间戳给服务器，返回动态列表
        String url=new String(DataUtils.BASEURL+DataUtils.INIT);

        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        params.put("location","123");
        params.put("time","149317700000");

        HttpUtil gettopic=HttpUtil.getInstance();

        gettopic.PostRequest(url, params, new HttpUtil.Listen() {
            @Override
            public void getResult(String result) {

                if (!result.equals("")){
                    JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                    JsonArray jsonArray=jsonObject.getAsJsonArray("list");
                    Gson gson=new Gson();
                    for (JsonElement bean:jsonArray){
                        TopicBean topic=gson.fromJson(bean,new TypeToken<TopicBean>(){ }.getType());
                        list.add(topic);
                    }
                }
            }
        });

    }



    @Override
    /*返回该动态下点赞的人的列表*/
    public ArrayList<UserBean> getLikeList(int tid) {
        String url = new String(DataUtils.BASEURL + DataUtils.GETLIKE);
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);

        HttpUtil getlikelist = HttpUtil.getInstance();
        String result = getlikelist.PostRequest(url, params);
        ArrayList<UserBean> likeList = new ArrayList<>();
        Log.i(""+tid,result);

        if (!result.equals("")) {
            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
            int status = jsonObject.get("status").getAsInt();
            if (status == -1) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("list");
                Gson gson = new Gson();
                for (JsonElement bean : jsonArray) {
                    UserBean user = gson.fromJson(bean, new TypeToken<UserBean>() {
                    }.getType());
                    likeList.add(user);
                }
            }
        }
        return likeList;
    }


    @Override
    /*返回该动态下的评论列表*/
    public ArrayList<CommentBean> getComment(int tid) {
        String url = new String(DataUtils.BASEURL + DataUtils.GETCOMMENT);

        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);

        HttpUtil getComment = HttpUtil.getInstance();
        String result = getComment.PostRequest(url, params);
        ArrayList<CommentBean> commentList = new ArrayList<>();

        Log.i(""+tid,result);
        if (!result.equals("")) {
            JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
            int status=jsonObject.get("status").getAsInt();
            if (status == 1) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("list");

                Gson gson = new Gson();
                for (JsonElement bean : jsonArray) {
                    CommentBean comment = gson.fromJson(bean, new TypeToken<CommentBean>() {
                    }.getType());
                    commentList.add(comment);
                }
            }
        }
        return commentList;
    }

}
