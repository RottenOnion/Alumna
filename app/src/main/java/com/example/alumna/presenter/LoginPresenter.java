package com.example.alumna.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.alumna.MyApplication;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.LoginModelImpl;
import com.example.alumna.model.LoginModel;
import com.example.alumna.presenter.Interface.LoginPresenterImpl;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.view.Interface.LoginViewImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.Call;

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

    public void Login(){
        lModel.Login(lView.getLoginImfor(), new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                lView.showProgressBar();
            }

            @Override
            public void onFinish() {
                lView.hideProgressBar();
            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                String status=jsonObject.get("status").getAsString();
                if (status.equals("1")) {
                    JsonObject user = jsonObject.get("user").getAsJsonObject();
                    Gson gson = new Gson();
                    UserBean userBean = gson.fromJson(user, UserBean.class);
                    Log.i("user",userBean.toString());
                    lView.StartMainActivity();
                }else if (status.equals("-1")){
                    Toast.makeText(MyApplication.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                    onFinish();
                }else if(status.equals("-2")){
                    Toast.makeText(MyApplication.getContext(),"用户名不存在",Toast.LENGTH_SHORT).show();
                    onFinish();
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }
}
