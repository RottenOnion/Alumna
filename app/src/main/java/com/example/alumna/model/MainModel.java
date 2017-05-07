package com.example.alumna.model;

import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    public void getTopicList(int uid,HttpRequestCallback callback) {
        //发送uid和时间戳给服务器，返回动态列表
        String url=new String(DataUtils.BASEURL+DataUtils.GETTOPIC
        );

        //TimerStat time=new TimerStat(1493177167000);
        Map<String,Object> params=new HashMap<>();
        params.put("uid",uid);
        //params.put("location","123");
        //params.put("time",time);

        HttpUtil gettopic=HttpUtil.getInstance();
        gettopic.PostRequest(url, params, callback);
    }


    @Override
    /*点赞*/
    public void setLike(int uid, int tid) {

    }

    @Override
    /*返回该动态下点赞的人的列表*/
    public ArrayList<UserBean> getLikeList(int tid) {
        return null;
    }

    @Override
    /*评论*/
    public void setComment(int uid, int tid, String comment) {

    }

    @Override
    /*返回该动态下的评论列表*/
    public void getComment(int tid,HttpRequestCallback callback) {
        String url=new String(DataUtils.BASEURL+DataUtils.GETTOPIC);

        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);

        HttpUtil getComment=HttpUtil.getInstance();
        getComment.PostRequest(url, params, callback);

    }
}
