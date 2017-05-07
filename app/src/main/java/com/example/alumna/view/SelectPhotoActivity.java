package com.example.alumna.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.alumna.presenter.SelectPhotoPresenter;
import com.example.alumna.view.Interface.SelectPhotoViewImpl;

/**
 * Created by Administrator on 2017/4/25.
 */

public class SelectPhotoActivity extends Activity implements View.OnClickListener, SelectPhotoViewImpl {


    //private Button select;
    private SelectPhotoPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //findview

        //setlistener

        presenter = new SelectPhotoPresenter(this);

        presenter.loadImage();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void showImage() {

    }
}
