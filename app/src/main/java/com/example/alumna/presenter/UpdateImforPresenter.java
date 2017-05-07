package com.example.alumna.presenter;


import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.UpdateImforModelImpl;
import com.example.alumna.model.UpdateImforModel;
import com.example.alumna.presenter.Interface.UpdateImforPresenterImpl;
import com.example.alumna.view.Interface.UpdateImforViewImpl;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UpdateImforPresenter implements UpdateImforPresenterImpl {
    private UpdateImforModelImpl uModel;
    private UpdateImforViewImpl uView;

    public UpdateImforPresenter(UpdateImforViewImpl view) {
        this.uView = view;
        uModel = new UpdateImforModel();
    }

    @Override
    public void loadImfor(int uid) {
        uView.showImfor(uModel.getUser(uid));
    }

    @Override
    public void UpdateImfor(UserBean user) {
        uModel.setUser(user);
    }
}
