package com.example.alumna.presenter;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberCircleModelImpl;
import com.example.alumna.model.MemberCircleModel;
import com.example.alumna.presenter.Interface.MemberCirclePresenterImpl;
import com.example.alumna.presenter.listener.OnMemberCircleListener;
import com.example.alumna.view.Interface.MemberCircleViewImpl;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/24.
 */

public class MemberCirclePresenter implements OnMemberCircleListener,MemberCirclePresenterImpl {

    private MemberCircleModelImpl model;
    private MemberCircleViewImpl view;

    public MemberCirclePresenter(MemberCircleViewImpl view){
        this.view=view;
        model=new MemberCircleModel(this);
    }
    @Override
    public void onLoadCircleSuccess(ArrayList<TopicBean> list) {
        view.showCircle(list);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoadFail() {

    }

    @Override
    public void onLoadUserSuccess(UserBean user) {
        view.showUser(user);
    }


    @Override
    public void loadUser(int uid) {
        model.getUser(uid);
    }

    @Override
    public void loadUserCircle(int uid) {
        model.getCircle(uid);
    }
}
