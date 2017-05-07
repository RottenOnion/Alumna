package com.example.alumna.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.UpdateImforPresenter;
import com.example.alumna.view.Interface.UpdateImforViewImpl;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UpdateImforActivity extends Activity implements View.OnClickListener, UpdateImforViewImpl {

    private UpdateImforPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //findview


        //set listener


        presenter = new UpdateImforPresenter(this);


        //load
        presenter.loadImfor(1);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showImfor(UserBean user) {

    }

    @Override
    public void saveImfor() {
        //gettext from editview
    }
}
