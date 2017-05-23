package com.example.alumna.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private int uid;
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
        uid=Integer.parseInt(mUid);

        presenter = new MemberPresenter(this);


        //load
        presenter.loadUser(uid);
        //presenter.loadTopicList(1);

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
        textName.setText(user.getUsername());
        textSchool.setText(user.getSchool());
        textLocation.setText(user.getLocation());
        textSignature.setText(user.getSignature());
        textWechat.setText(user.getWechat());

        //head
        Glide.with(this).load(user.getHead()).error(R.drawable.ic_default_head).into(headView);


        //gender
        if (user.getSex()==1){
            btnGender.setText("男");
            btnGender.setBackground(getResources().getDrawable(R.drawable.button_male_shape));
        }else if (user.getSex()==2){
            btnGender.setText("女");
            btnGender.setBackground(getResources().getDrawable(R.drawable.button_female_shape));
        }

        //grade
        switch (user.getGrade()){
            case "1":btnGrade.setText("大一");break;
            case "2":btnGrade.setText("大二");break;
            case "3":btnGrade.setText("大三");break;
            case "4":btnGrade.setText("大四");break;
            default:btnGender.setText(user.getGrade());
        }
    }

    @Override
    public void showTopicList(ArrayList<TopicBean> list) {

    }


}
