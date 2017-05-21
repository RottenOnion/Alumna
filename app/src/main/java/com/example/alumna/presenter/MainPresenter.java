package com.example.alumna.presenter;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.model.MainModel;
import com.example.alumna.presenter.Interface.MainPresenterImpl;
import com.example.alumna.presenter.Interface.OnMainListener;
import com.example.alumna.view.Interface.MainViewImpl;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/4/25.
 */

public class MainPresenter implements MainPresenterImpl ,OnMainListener{

    private MainViewImpl mView;
    private MainModelImpl mModel;

    public MainPresenter(MainViewImpl view) {
        mView = view;
        mModel = new MainModel(this);
    }

    @Override
    public void loadTopicList(final int uid) {
        mView.showProgressBar();
        mModel.getTopicList(uid);
    }

    @Override
    public void TopicSuccess(final ArrayList<TopicBean> list) {
        for (final TopicBean topic:list){
            if(topic.getLikeNum()>0){
                mModel.getLikeList(topic.getTid(),new MainModel.OnLikeListResult(){
                    @Override
                    public void success(ArrayList<UserBean> likelist) {
                        topic.setLikeList(likelist);
                    }
                });
            }
            if (topic.getCommentNum()>0){
                mModel.getComment(topic.getTid(), new MainModel.OnCommentResult() {
                    @Override
                    public void success(ArrayList<CommentBean> commentlist) {
                        topic.setCommentList(commentlist);
                    }
                });
            }
        }
        mView.showTopicList(list);
        mView.hideProgressBar();
    }

    @Override
    public void LikeListSuccess(ArrayList<UserBean> list) {

    }


    @Override
    public void CommentListSuccess(ArrayList<CommentBean> list) {

    }

    @Override
    public void onError() {

    }


}
