package com.example.alumna.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alumna.R;
import com.example.alumna.adapter.TopicListAdapter.FriendCircleAdapter;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.MemberCirclePresenter;
import com.example.alumna.view.Interface.MemberCircleViewImpl;
import com.mingle.widget.LoadingView;

import java.util.ArrayList;

public class MemberCircleActivity extends AppCompatActivity implements MemberCircleViewImpl{

    private RecyclerView topiclist;
    private FriendCircleAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private TextView nameTv;
    private ImageView headIv,bgIv;
    private MemberCirclePresenter presenter;
    //加载view
    private LoadingView loadingView;

    private int uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
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
        topiclist = (RecyclerView) findViewById(R.id.topiclist);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        nameTv=(TextView)findViewById(R.id.nameTv);
        bgIv=(ImageView)findViewById(R.id.backgroundIv);
        headIv=(ImageView)findViewById(R.id.headIv);

        loadingView = (LoadingView) findViewById(R.id.loading_view);
        loadingView.setVisibility(View.VISIBLE);

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
        nameTv.setText(user.getUsername());
        Glide.with(this).load(user.getHead()).into(headIv);
        Glide.with(this).load(user.getBackground()).into(bgIv);
    }

    @Override
    public void hideLoadView() {
        loadingView.setVisibility(View.GONE);
        refreshLayout.setRefreshing(false);
    }

}
