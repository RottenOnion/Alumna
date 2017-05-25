package com.example.alumna.presenter;

import android.os.Handler;
import android.util.Log;

import com.example.alumna.MyApplication;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MainModelImpl;
import com.example.alumna.model.MainModel;
import com.example.alumna.presenter.Interface.MainPresenterImpl;
import com.example.alumna.presenter.listener.OnMainListener;
import com.example.alumna.utils.LocationUtil;
import com.example.alumna.view.Interface.MainViewImpl;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;


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
        mModel.getTopicList(uid);
    }

    @Override
    public void loadFriendList(int uid) {
        mModel.getFriend(uid);
    }



    public void uploadBackground(int uid, ArrayList<ImageItem> img) {
        mModel.uploadImage(img);
    }

    @Override
    public void TopicSuccess(final ArrayList<TopicBean> list) {

        //避免数据未装载就返回票圈，这里给延时3秒
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mView.showFriendCircle(list);
                mView.hideProgress();
            }
        }, 3000);
    }

    @Override
    public void onFriendSuccess(List<UserBean> list) {
        mView.showFriend(list);
    }

    @Override
    public void UploadSuccess(String url) {
        Log.i("url",url);
        mModel.updateBg(MyApplication.getcurUser().getUid(),url);
    }

    @Override
    public void UploadSuccess() {
        //背景上传成功
    }

    @Override
    public void onError() {
        mView.hideProgress();
    }

    public void loadLocation(){
        LocationUtil.getInstance().getLocation(new LocationUtil.getLocationCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String location, String coordinate) {

            }

            @Override
            public void onFailure(String result) {

            }
        });
    }
}
