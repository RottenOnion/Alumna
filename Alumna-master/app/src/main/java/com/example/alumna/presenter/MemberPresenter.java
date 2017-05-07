package com.example.alumna.presenter;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberModelImpl;
import com.example.alumna.model.MemberModel;
import com.example.alumna.presenter.Interface.MemberPresenterImpl;
import com.example.alumna.view.Interface.MemberViewImpl;

import java.util.ArrayList;



public class MemberPresenter implements MemberPresenterImpl {
    private MemberViewImpl mView;
    private MemberModelImpl mModel;

    public MemberPresenter(MemberViewImpl view) {
        mView = view;
        mModel = new MemberModel();
    }

    @Override
    public void loadUser(int id) {

        UserBean user = mModel.getUser(id);

        mView.showUserImfor(user);
    }

    @Override
    public void loadTopicList(int uid) {
        ArrayList<TopicBean> list = mModel.getTopicList(uid);

        mView.showTopicList(list);
    }


}
