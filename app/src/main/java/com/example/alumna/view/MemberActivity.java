package com.example.alumna.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.MemberPresenter;
import com.example.alumna.view.Interface.MemberViewImpl;

import java.util.ArrayList;


public class MemberActivity extends Activity implements OnClickListener, MemberViewImpl {

    private MemberPresenter presenter;
    private ImageView headView;
    private TextView textName,textSchool,textLocation,textSignature,textWechat;
    private Button btnGender,btnGrade;
    private String mUid;
    private LinearLayout layoutCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        //findview
        initView();

        //get Uid
        Intent intent = getIntent();
        mUid = intent.getStringExtra("uid");


        presenter = new MemberPresenter(this);


        //load
        presenter.loadUser(1);
        presenter.loadTopicList(1);

    }

    private void initView() {
        headView = (ImageView) findViewById(R.id.head_view);
        textName = (TextView) findViewById(R.id.text_name);
        textSchool = (TextView) findViewById(R.id.text_school);
        textLocation = (TextView) findViewById(R.id.text_location);
        textSignature = (TextView) findViewById(R.id.text_signature);
        textWechat = (TextView) findViewById(R.id.text_wechat);
        btnGender = (Button) findViewById(R.id.btn_gender);
        btnGrade = (Button) findViewById(R.id.btn_grade);
        layoutCircle = (LinearLayout) findViewById(R.id.layout_friend_circle);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;

        }
    }


    @Override
    public void showUserInform(UserBean user) {
        Log.d("ccl",user.toString());
    }

    @Override
    public void showTopicList(ArrayList<TopicBean> list) {

    }


}
