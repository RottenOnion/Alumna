package com.example.alumna.presenter.Interface;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.utils.Http.HttpRequestCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainPresenterImpl {

    void loadBackground(int uid);

    void loadImfor(int uid);

    void loadTopicList(int uid);

    //void showTopicList(ArrayList<TopicBean>list);

    void LikeTopic(int uid, int tid);

    void CommentTopic(int uid, int tid, String comment);

    void getCommentList(int tid);
}
