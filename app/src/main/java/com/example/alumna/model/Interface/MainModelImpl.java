package com.example.alumna.model.Interface;


import com.example.alumna.model.MainModel;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainModelImpl {

    void getTopicList(int uid);

    void getLikeList(int tid, MainModel.OnLikeListResult listResult);

    void getComment(int tid,MainModel.OnCommentResult listResult);

    void getFriend(int uid);

}
