package com.example.alumna.model.Interface;


import com.example.alumna.model.MainModel;

import com.example.alumna.utils.Http.HttpRequestCallback;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainModelImpl {

    void getTopicList(int uid);

    void getLikeList(int tid, MainModel.OnLikeListResult listResult);

    void getComment(int tid,MainModel.OnCommentResult listResult);

    void getcurUser(final int uid);

    void getFriend(int uid);

    void uploadImage(ArrayList<ImageItem>imgs);

    void updateBg(final int uid,final String img);


}
