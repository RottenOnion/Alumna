package com.example.alumna.view;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.presenter.PublishPresenter;
import com.example.alumna.utils.LocationUtil;
import com.example.alumna.view.Interface.PublishViewImpl;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class PublishActivity extends AppCompatActivity implements View.OnClickListener, PublishViewImpl {

    private PublishPresenter presenter;

    private EditText topicEt;
    private TextView locationTv;
    private Button publishBtn;
    private Toolbar toolbar;
    private Button backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_topic);
        //findview
        topicEt=(EditText)findViewById(R.id.topicEt) ;
        locationTv=(TextView) findViewById(R.id.publish_location_tv);
        publishBtn=(Button)findViewById(R.id.send_Btn);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        backBtn=(Button)findViewById(R.id.back_Btn) ;
        setSupportActionBar(toolbar);

        //setlistener
        backBtn.setOnClickListener(this);
        publishBtn.setOnClickListener(this);
        publishBtn.setClickable(false);

        topicEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    publishBtn.setClickable(true);
                }else  publishBtn.setClickable(false);

            }
        });

        presenter = new PublishPresenter(this);

        presenter.getLocation();


    }



    @Override
    public void showSelectedImage(ArrayList<String> imagelist) {

    }

    @Override
    public void setLocationText(String location) {
        locationTv.setText(location);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_Btn:
                presenter.publish(topicEt.getText().toString());
                break;
            case R.id.back_Btn:
                PublishActivity.this.finish();
            default:break;
        }
    }
}
