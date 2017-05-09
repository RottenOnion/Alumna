package com.example.alumna.adapter.TopicListAdapter;

/**
 * Created by Leebobo on 2017/5/9.
 */

public class TopicListPresenter implements TopicListPresenetImpl{
    private TopicListModel tModel;
    private TopicListAdapter tView;

    public TopicListPresenter(TopicListAdapter view){
        tView=view;
        tModel=new TopicListModel();
    }


    @Override
    public void loadLikeList() {

    }

    @Override
    public void loadCommentList() {

    }

    @Override
    public void setLike() {

    }

    @Override
    public void setComment() {

    }

    @Override
    public void getUserImfor() {

    }
}
