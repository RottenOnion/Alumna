package com.example.alumna.presenter;

import com.example.alumna.model.Interface.PublishModelImpl;
import com.example.alumna.model.PublishModel;
import com.example.alumna.presenter.Interface.PublishPresenterImpl;
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
    public void publish() {
        //发布动态
        pModel.PublishTopic();
    }
}
