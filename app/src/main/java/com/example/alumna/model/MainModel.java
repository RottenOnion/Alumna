package com.example.alumna.model;

import android.util.Log;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.presenter.listener.OnMainListener;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/4/25.
 */

public class MainModel implements MainModelImpl {

    OnMainListener mListener;

    public MainModel(OnMainListener mainListener){
        this.mListener=mainListener;
    }

    @Override
    public void getTopicList(int uid) {
        //发送uid和时间戳给服务器，返回动态列表
        String url=new String(DataUtils.BASEURL+DataUtils.INIT);

        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        params.put("location","123");
        params.put("time","1493177167");

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
                        getLikeList(topic.getTid(),new OnLikeListResult(){
                            @Override
                            public void success(ArrayList<UserBean> likelist) {
                                topic.setLikeList(likelist);
                            }
                        });
                    }
                    if (topic.getCommentNum()>0){
                        getComment(topic.getTid(), new OnCommentResult() {
                            @Override
                            public void success(ArrayList<CommentBean> commentlist) {
                                topic.setCommentList(commentlist);
                            }
                        });
                    }
                    list.add(topic);
                }
                mListener.TopicSuccess(list);
            }

            @Override
            public void onFailure(Call call) {
                mListener.onError();
            }
        });

    }


    @Override
    /*返回该动态下点赞的人的列表*/
    public void getLikeList(final int tid, final OnLikeListResult listener) {

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



    /**
     * 获取好友列表
     * @param uid
     */
    @Override
    public void getFriend(int uid) {
        //获得好友列表
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        HttpUtil.getInstance().PostRequest(DataUtils.BASEURL + DataUtils.MYFRIEND, map,
                new HttpRequestCallback<String>() {
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
                            List<UserBean> userList = new ArrayList<>();
                            for (JsonElement user : jsonArray) {
                                UserBean userBean = gson.fromJson(user, new TypeToken<UserBean>() {
                                }.getType());
                                userList.add(userBean);
                            }
                            mListener.onFriendSuccess(userList);

                            Log.d("cao", "好友列表拿到");
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call call) {

                    }
                });
    }

    @Override
    public void uploadImage(ArrayList<ImageItem> imgs) {
        String url=DataUtils.BASEURL+DataUtils.SENDPIC;
        HttpUtil.getInstance().UploadImage(url, imgs, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                String imageurl=jsonObject.get("url").getAsString();
                mListener.UploadSuccess(imageurl);
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    @Override
    public void updateBg(int uid, String img) {
        String url=DataUtils.BASEURL+DataUtils.SETBACKGROUND;
        HashMap<String,Object>params=new HashMap<>();
        params.put("uid",uid);
        params.put("background",img);
        HttpUtil.getInstance().PostRequest(url, params, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                Log.i("updatebg",result);
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                int status=jsonObject.get("status").getAsInt();
                if (status==1)mListener.UploadSuccess();
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
