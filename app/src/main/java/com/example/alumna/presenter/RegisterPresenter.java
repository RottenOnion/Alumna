package com.example.alumna.presenter;

import com.example.alumna.MyApplication;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.RegisterModelImpl;
import com.example.alumna.model.RegisterModel;
import com.example.alumna.presenter.Interface.RegisterPresenterImpl;
import com.example.alumna.presenter.listener.OnRegisterListener;
import com.example.alumna.view.Interface.RegisterViewImpl;

import java.util.HashMap;

/**
 * Created by Leebobo on 2017/5/23.
 */

public class RegisterPresenter implements OnRegisterListener,RegisterPresenterImpl{

    private RegisterViewImpl rView;
    private RegisterModelImpl rModel;
    public RegisterPresenter(RegisterViewImpl view){
        rView=view;
        rModel=new RegisterModel(this);
    }

    @Override
    public void onRegisterSuccess(UserBean user) {
        //注册成功
        MyApplication.setCurUser(user);
        rView.RegisterSuccess();
    }

    @Override
    public void onRegisterFailure(int status) {
        rView.showStatus(status);
    }

    @Override
    public void onError() {

    }

    @Override
    public void register() {
        HashMap<String,Object>params =new HashMap<>();
        params.put("phone",rView.getPhone());
        params.put("username",rView.getUsername());
        params.put("password",rView.getPassword());
        rModel.register(params);
    }
}
