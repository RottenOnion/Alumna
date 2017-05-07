package com.example.alumna.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.alumna.presenter.PublishPresenter;
import com.example.alumna.view.Interface.PublishViewImpl;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class PublishActivity extends Activity implements DialogInterface.OnClickListener, PublishViewImpl {

    private PublishPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //findview

        //setlistener

        presenter = new PublishPresenter(this);

        presenter.loadSelectedImage();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void showSelectedImage(ArrayList<String> imagelist) {

    }
}
