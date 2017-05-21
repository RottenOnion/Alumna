package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.util.Log;
import android.view.View;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.Call;


/**
 * Created by Leebobo on 2017/5/9.
 */

public class ViewHolderPresenter implements ViewHolderPresenterImpl{

    private TopicListViewHolder vhView;
    private ViewHolderModel vhModel;

    ViewHolderPresenter(TopicListViewHolder view){
        vhView=view;
        vhModel=new ViewHolderModel();
    }

    @Override
    public void loadLikeList(final int tid) {
        vhModel.getLikeList(tid, new HttpRequestCallback<String>() {
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
                if (status==1){
                    JsonArray jsonArray=jsonObject.getAsJsonArray("list");
                    Gson gson=new Gson();
                    ArrayList<UserBean> likeList=new ArrayList<>();
                    for (JsonElement bean:jsonArray){
                        UserBean user=gson.fromJson(bean,new TypeToken<UserBean>(){ }.getType());
                        likeList.add(user);
                    }
                    vhView.setPraiseListView(likeList);
                    vhView.praiseListView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    @Override
    public void loadCommentList(final int tid) {
        vhModel.getCommentList(tid, new HttpRequestCallback<String>() {
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
                if (status==1){
                    JsonArray jsonArray=jsonObject.getAsJsonArray("list");

                    Gson gson=new Gson();
                    ArrayList<CommentBean> commentlist=new ArrayList<>();
                    for (JsonElement bean:jsonArray){
                        CommentBean comment=gson.fromJson(bean,new TypeToken<CommentBean>(){ }.getType());
                        commentlist.add(comment);
                    }
                    vhView.setCommentListView(commentlist);
                    vhView.commentListView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    @Override
    public void setLike(final int uid,final int tid) {
        vhModel.setLike(uid, tid, new HttpRequestCallback<String>(){
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                if (jsonObject.get("status").getAsString().equals("1")||jsonObject.get("status").getAsString().equals("0")){
                    //操作成功status=0 or status=1
                    loadLikeList(tid);
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });

    }

    @Override
    public void setComment(final int uid,final int tid,final String comment) {
        vhModel.setComment(uid, tid, comment, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                vhView.editTextPopupWindow.dismiss();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                vhView.editTextPopupWindow.clearAllText();
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                if (jsonObject.get("status").getAsString().equals("1")){
                    //操作成功status=0 or status=1
                    loadCommentList(tid);
                }

            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    @Override
    public void getUserImfor(final int uid) {
        vhModel.getUserImfor(uid, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                //JsonArray jsonArray=jsonObject.getAsJsonArray("user");
                //Gson gson=new Gson();
                Log.i(this.getClass().getName(),result);
            }

            @Override
            public void onFailure(Call call) {

            }
        });

    }
}
