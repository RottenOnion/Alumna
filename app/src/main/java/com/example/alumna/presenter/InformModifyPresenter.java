package com.example.alumna.presenter;


import com.example.alumna.MyApplication;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.InformModifyModelImpl;
import com.example.alumna.model.InformModifyModel;
import com.example.alumna.presenter.Interface.InformModifyPresenterImpl;
import com.example.alumna.presenter.listener.OnModifyListener;
import com.example.alumna.view.Interface.InformModifyViewImpl;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.HashMap;
import java.util.List;

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
    public void UpdateImfor(int uid) {
        HashMap<String,Object>params=uView.modifyInform();
        params.put("uid",uid);
        uModel.InformModify(params);
    }

    @Override
    public void UploadImage(final List<ImageItem> imgs) {
        uModel.UploadImage(imgs);
    }

    @Override
    public void OnLoadUserSuccess(UserBean user) {
        uView.showInform(user);
    }

    @Override
    public void OnUploadImageSuccess(String url) {
        uView.ImageUploadSuccess(url);
        uView.showImageToast();
    }

    @Override
    public void OnModifyInformSuccess(UserBean user) {
        MyApplication.setCurUser(user);
        uView.showInformToast();
    }

    @Override
    public void OnError() {

    }
}
