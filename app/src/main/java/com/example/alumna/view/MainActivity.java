package com.example.alumna.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.alumna.MyApplication;
import com.example.alumna.adapter.LeftDrawerAdapter;
import com.example.alumna.adapter.TopicListAdapter.FriendCircleAdapter;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.LeftBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.R;
import com.example.alumna.presenter.MainPresenter;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.view.Interface.MainViewImpl;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainViewImpl {

    private RecyclerView mLeftRvView;
    private ArrayList<LeftBean> mLeftDatas;

    private RecyclerView topiclist;
    FriendCircleAdapter adapter;

    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new MainPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mLeftRvView = (RecyclerView) findViewById(R.id.main_left_recycler);


        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*
         初始化左侧滑菜单Item
         */
        loadLeftDatas();
        LeftDrawerAdapter leftAdapter = new LeftDrawerAdapter(mLeftDatas);
        mLeftRvView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mLeftRvView.setAdapter(leftAdapter);

        {
            topiclist = (RecyclerView) findViewById(R.id.topiclist);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            topiclist.setLayoutManager(layoutManager);
            presenter.loadTopicList(DataUtils.curUser.getUid());

        }


    }


    private void loadLeftDatas() {
        mLeftDatas = new ArrayList<>();
        LeftBean data = new LeftBean(R.drawable.ic_menu_friend,"朋友圈");
        mLeftDatas.add(data);

        data = new LeftBean(R.drawable.ic_menu_setting,"设置");
        mLeftDatas.add(data);

        data = new LeftBean(R.drawable.ic_menu_about,"关于");
        mLeftDatas.add(data);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showBackground(String url) {

    }

    @Override
    public void showImfor(UserBean user) {

    }

    @Override
    public void showTopicList(ArrayList<TopicBean> list) {
        adapter=new FriendCircleAdapter(MyApplication.getContext(),list);
        topiclist.setAdapter(adapter);
    }

    @Override
    public void showComment(ArrayList<CommentBean> list) {

    }
}
