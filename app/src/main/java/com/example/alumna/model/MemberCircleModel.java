package com.example.alumna.model;

import android.util.Log;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberCircleModelImpl;
import com.example.alumna.presenter.listener.OnMemberCircleListener;
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
 * Created by Leebobo on 2017/5/24.
 */

public class MemberCircleModel implements MemberCircleModelImpl{

    private OnMemberCircleListener mlistener;
    public MemberCircleModel(OnMemberCircleListener listener){
        mlistener=listener;
    }


    @Override
    public void getUser(final int uid) {
        Map<String,Object> map = new HashMap<>();
        final String mUrl = DataUtils.BASEURL + DataUtils.MEMBER;

        map.put("uid",uid);
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
                    mlistener.onLoadUserSuccess(userBean);
                }
            }

            @Override
            public void onFailure(Call call) {
            }
        });
    }

    @Override
    public void getCircle(int uid) {
        String url=new String(DataUtils.BASEURL+DataUtils.GETTOPIC);

        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);

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
                JsonArray jsonArray = jsonObject.get("list").getAsJsonArray();
                Gson gson = new Gson();
                ArrayList<TopicBean> list = new ArrayList<>();
                for (JsonElement bean : jsonArray) {
                    final TopicBean topic = gson.fromJson(bean, new TypeToken<TopicBean>() {}.getType());

                    if(topic.getLikeNum()>0){
                        getlikeList(topic.getTid(),new OnLikeListResult(){
                            @Override
                            public void success(ArrayList<UserBean> likelist) {
                                topic.setLikeList(likelist);
                            }
                        });
                    }
                    if (topic.getCommentNum()>0){
                        getCommentList(topic.getTid(), new OnCommentResult(){
                            @Override
                            public void success(ArrayList<CommentBean> commentlist) {
                                topic.setCommentList(commentlist);
                            }
                        });
                    }
                    list.add(topic);
                    mlistener.onLoadCircleSuccess(list);
                }
            }

            @Override
            public void onFailure(Call call) {
                mlistener.onError();
            }
        });

    }

    @Override
    public void getlikeList(final int tid, final OnLikeListResult listener) {
        String url = new String(DataUtils.BASEURL + DataUtils.GETLIKE);
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);

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
                mlistener.onError();
            }
        });
    }

    @Override
    public void getCommentList(final int tid, final OnCommentResult listener) {
        String url = new String(DataUtils.BASEURL + DataUtils.GETCOMMENT);

        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);

        HttpUtil.getInstance().PostRequest(url, params, new HttpRequestCallback<String>() {
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
                        CommentBean comment = gson.fromJson(bean, new TypeToken<CommentBean>() {}.getType());
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

    public interface OnCommentResult{
        void success(ArrayList<CommentBean> list);
    }

    public interface OnLikeListResult{
        void success(ArrayList<UserBean> list);
    }
}
