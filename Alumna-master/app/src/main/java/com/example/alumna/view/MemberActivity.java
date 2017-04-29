package com.example.alumna.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.MemberPresenter;
import com.example.alumna.view.Interface.MemberViewImpl;

import java.util.ArrayList;


public class MemberActivity extends Activity implements OnClickListener, MemberViewImpl {

    private MemberPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //findview


        //set listener


        presenter = new MemberPresenter(this);


        //load
        presenter.loadUser(1);
        presenter.loadTopicList(1);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;

        }
    }


    @Override
    public void showUserImfor(UserBean user) {

    }

    @Override
    public void showTopicList(ArrayList<TopicBean> list) {

    }


}
