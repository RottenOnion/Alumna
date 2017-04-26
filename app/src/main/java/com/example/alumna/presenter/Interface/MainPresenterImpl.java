package com.example.alumna.presenter.Interface;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainPresenterImpl {

    void loadBackground(int uid);

    void loadImfor(int uid);

    void loadTopicList(int uid);

    void LikeTopic(int uid, int tid);

    void CommentTopic(int uid, int tid, String comment);
}
