package com.example.alumna.presenter;


import android.util.Log;

import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.InformModifyModelImpl;
import com.example.alumna.model.InformModifyModel;
import com.example.alumna.presenter.Interface.InformModifyPresenterImpl;
import com.example.alumna.presenter.listener.OnModifyListener;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.view.Interface.InformModifyViewImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/25.
 */

public class InformModifyPresenter implements InformModifyPresenterImpl,OnModifyListener {
    private InformModifyModelImpl uModel;
    private InformModifyViewImpl uView;

    public InformModifyPresenter(InformModifyViewImpl view) {
        this.uView = view;
        uModel = new InformModifyModel(this);
    }

    @Override
    public void loadImfor(int uid) {
        uModel.getUser(uid);
    }


    @Override
    public void UpdateImfor(UserBean user) {
        uModel.setUser(user);
    }

    @Override
    public void UploadImage(final List<ImageItem> imgs) {
        uModel.UploadImage(imgs);
    }

    @Override
    public void OnLoadUserSuccess(UserBean user) {
        uView.showImfor(user);
    }

    @Override
    public void OnUploadImageSuccess() {

    }

    @Override
    public void OnModifyInformSuccess() {

    }

    @Override
    public void OnError() {

    }
}
