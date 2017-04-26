package com.example.alumna.presenter;

import com.example.alumna.model.Interface.LoginModelImpl;
import com.example.alumna.model.LoginModel;
import com.example.alumna.presenter.Interface.LoginPresenterImpl;
import com.example.alumna.view.Interface.LoginViewImpl;

/**
 * Created by Leebobo on 2017/4/26.
 */

public class LoginPresenter implements LoginPresenterImpl{

    private LoginViewImpl lView;
    private LoginModelImpl lModel;
    public LoginPresenter(LoginViewImpl view){
        lView=view;
        lModel=new LoginModel();
    }
}
