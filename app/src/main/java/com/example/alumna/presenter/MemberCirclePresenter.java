package com.example.alumna.presenter;

import com.example.alumna.bean.TopicBean;
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
    public void onLoadSuccess(ArrayList<TopicBean> list) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoadFail() {

    }
}
