package com.example.alumna.presenter;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberModelImpl;
import com.example.alumna.model.MemberModel;
import com.example.alumna.presenter.Interface.MemberPresenterImpl;
import com.example.alumna.presenter.listener.OnMemberListener;
import com.example.alumna.view.Interface.MemberViewImpl;

import java.util.ArrayList;



public class MemberPresenter implements MemberPresenterImpl,OnMemberListener {
    private MemberViewImpl mView;
    private MemberModelImpl mModel;

    public MemberPresenter(MemberViewImpl view) {
        mView = view;
        mModel = new MemberModel(this);
    }

    @Override
    public void loadUser(int id) {

        mModel.getUser(id);

    }

    @Override
    public void loadTopicList(int uid) {
    }


    @Override
    public void onUserSuccess(UserBean user) {

        mView.showUserInform(user);
    }

    @Override
    public void onTopicSuccess(ArrayList<TopicBean> list) {
        mView.showTopicList(list);
    }

    @Override
    public void onError() {

    }
}
