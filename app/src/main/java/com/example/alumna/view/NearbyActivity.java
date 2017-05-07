package com.example.alumna.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.NearbyPresenter;
import com.example.alumna.view.Interface.NearbyViewImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyActivity extends Activity implements DialogInterface.OnClickListener, NearbyViewImpl {

    private NearbyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //findview


        //set listener


        presenter = new NearbyPresenter(this);


        //load
        presenter.loadNearby();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void showNearby(ArrayList<UserBean> list) {

    }
}
