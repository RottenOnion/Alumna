package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.util.Log;

import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * Created by Leebobo on 2017/5/9.
 */

class ViewHolderModel {
    public void getLikeList(int tid,HttpRequestCallback callback){
        String url=new String(DataUtils.BASEURL+DataUtils.GETLIKE);
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);

        HttpUtil getlikelist=HttpUtil.getInstance();
        getlikelist.PostRequest(url, params, callback);
    }

    public void getCommentList(int tid, HttpRequestCallback callback){
        String url=new String(DataUtils.BASEURL+DataUtils.GETCOMMENT);
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);

        HttpUtil getcomment=HttpUtil.getInstance();
        getcomment.PostRequest(url, params, callback);
    }

    public void setLike(int uid,int tid, HttpRequestCallback callback){
        String url=new String(DataUtils.BASEURL+DataUtils.SETLIKE);
        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        params.put("tid",tid);

        HttpUtil setLike=HttpUtil.getInstance();
        setLike.PostRequest(url, params,callback);
    }

    public void getUserImfor(int uid,HttpRequestCallback callback){
        String url=new String(DataUtils.BASEURL+DataUtils.GETUSER);
        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);

        HttpUtil getuser=HttpUtil.getInstance();
        getuser.PostRequest(url, params,callback);
    }

    public void setComment(int uid,int tid,String comment,HttpRequestCallback callback){
        String url=new String(DataUtils.BASEURL+DataUtils.SETCOMMENT);
        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        params.put("tid",tid);
        params.put("comment",comment);
        params.put("time", ""+new Date(System.currentTimeMillis()).getTime());

        HttpUtil setComment=HttpUtil.getInstance();
        setComment.PostRequest(url, params,callback);
    }
}
