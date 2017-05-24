package com.example.alumna.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.alumna.R;
import com.example.alumna.adapter.TopicListAdapter.FriendCircleAdapter;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.MemberCirclePresenter;
import com.example.alumna.view.Interface.MemberCircleViewImpl;

import java.util.ArrayList;

public class MemberCircleActivity extends AppCompatActivity implements MemberCircleViewImpl{

    private RecyclerView topiclist;
    private FriendCircleAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private MemberCirclePresenter presenter;

    private int uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_circle);
        presenter=new MemberCirclePresenter(this);
        init();

        //get Uid
        Intent intent = getIntent();
        String mUid = intent.getStringExtra("uid");
        uid=Integer.parseInt(mUid);

        presenter.loadUser(uid);
        presenter.loadUserCircle(uid);

    }

    private void init() {
        topiclist = (RecyclerView) findViewById(R.id.friend_circle);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                presenter.loadUserCircle(uid);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        topiclist.setLayoutManager(layoutManager);
    }


    @Override
    public void showCircle(ArrayList<TopicBean>list) {
        adapter=new FriendCircleAdapter(this,list);
        topiclist.setAdapter(adapter);
    }

    @Override
    public void showUser(UserBean user) {

    }

}
