package com.example.alumna.presenter;

import android.content.Context;
import android.location.LocationManager;

import com.example.alumna.model.Interface.PublishModelImpl;
import com.example.alumna.model.PublishModel;
import com.example.alumna.presenter.Interface.PublishPresenterImpl;
import com.example.alumna.utils.LocationUtil;
import com.example.alumna.view.Interface.PublishViewImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class PublishPresenter implements PublishPresenterImpl {
    private PublishViewImpl pView;
    private PublishModelImpl pModel;

    public PublishPresenter(PublishViewImpl view) {
        pView = view;
        pModel = new PublishModel();

    }

    @Override
    public void loadSelectedImage() {

        ArrayList<String> list = pModel.getSelectedImage();
        //show
        pView.showSelectedImage(list);

    }

    @Override
    public void publish(String topic) {
        //发布动态
        pModel.PublishTopic();
    }

    @Override
    public void getLocation() {
        final LocationUtil lo=LocationUtil.getInstance();
        lo.getLocation(new LocationUtil.getLocationCallback() {
            @Override
            public void onStart() {
                pView.setLocationText("定位中...");
            }

            @Override
            public void onFinish() {
                lo.stop();
            }

            @Override
            public void onSuccess(String result) {
                pView.setLocationText(result);
            }

            @Override
            public void onFailure(String result) {
                pView.setLocationText(result);
            }
        });
    }
}
