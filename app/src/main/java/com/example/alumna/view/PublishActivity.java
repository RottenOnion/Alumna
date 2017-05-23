package com.example.alumna.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.presenter.PublishPresenter;
import com.example.alumna.view.Interface.PublishViewImpl;
import com.example.alumna.widgets.ImageShower;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class PublishActivity extends AppCompatActivity implements View.OnClickListener, PublishViewImpl {

    private PublishPresenter presenter;

    private final static int TYPE_TEXT=1;
    private final static int TYPE_IMAGE=2;

    private boolean isImage=false;//默认文字
    private int type;
    private EditText topicEt;
    private TextView locationTv;
    private Button publishBtn;
    private Toolbar toolbar;
    private Button backBtn;
    private ImageShower imageListView;

    private ProgressBar progressbar;

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
        progressbar=(ProgressBar)findViewById(R.id.progress_bar);
        imageListView=(ImageShower)findViewById(R.id.img_list_view);


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

        isImage=this.getIntent().getBooleanExtra("flag",false);

        if(isImage){
            imageListView.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, TYPE_IMAGE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == TYPE_IMAGE) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imageListView.notifyDataSetChanged(images);
            }
        }
    }

    @Override
    public List<ImageItem> getSelectImg() {
        return imageListView.getImgs();
    }

    @Override
    public void showprogressbar(String message) {
        progressbar.setVisibility(View.VISIBLE);
        publishBtn.setClickable(false);
    }

    @Override
    public void hideprogressbar() {
        progressbar.setVisibility(View.GONE);
        publishBtn.setClickable(true);
    }

    @Override
    public void FinishAcitvity() {
        PublishActivity.this.finish();
    }

    @Override
    public void setLocationText(String location,String coordinate) {
        locationTv.setText(location);
        locationTv.setTag(R.id.coordinate,coordinate);
    }

    @Override
    public String getLocation() {
        return locationTv.getText().toString();
    }

    @Override
    public String getEditViewText() {
        return topicEt.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_Btn:
                type=imageListView.getImgsSize()>0?TYPE_IMAGE:TYPE_TEXT;
                presenter.publish(type);
                break;
            case R.id.back_Btn:
                PublishActivity.this.finish();
            default:break;
        }
    }
}
