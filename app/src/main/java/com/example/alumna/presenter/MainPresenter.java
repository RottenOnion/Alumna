package com.example.alumna.presenter;

import android.os.Handler;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.model.MainModel;
import com.example.alumna.presenter.Interface.MainPresenterImpl;
import com.example.alumna.presenter.listener.OnMainListener;
import com.example.alumna.view.Interface.MainViewImpl;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/4/25.
 */

public class MainPresenter implements MainPresenterImpl ,OnMainListener {

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
        //避免数据未装载就返回票圈，这里给延时3秒
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mView.showFriendCircle(list);
                mView.hideProgressBar();
            }
        }, 3000);
    }


    @Override
    public void onError() {
        mView.hideProgressBar();
    }


}
