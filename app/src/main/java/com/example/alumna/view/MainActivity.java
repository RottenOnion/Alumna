package com.example.alumna.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.adapter.LeftDrawerAdapter;
import com.example.alumna.adapter.LeftDrawerAdapter.onItemClickListener;
import com.example.alumna.adapter.TopicListAdapter.FriendCircleAdapter;
import com.example.alumna.bean.LeftBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.presenter.MainPresenter;
import com.example.alumna.view.Interface.MainViewImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainViewImpl {

    private RecyclerView mLeftRvView;
    private ArrayList<LeftBean> mLeftDatas;

    private RecyclerView topiclist;
    private FriendCircleAdapter adapter;

    private MainPresenter presenter;
    private Button publishBtn;
    private LinearLayout layoutCircle;
    private SwipeRefreshLayout refreshLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    private final static boolean TYPE_TEXT=false;
    private static final boolean TYPE_IMAGE=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new MainPresenter(this);

        mLeftRvView = (RecyclerView) findViewById(R.id.main_left_recycler);
        topiclist = (RecyclerView) findViewById(R.id.topiclist);
        publishBtn=(Button)findViewById(R.id.publish_Btn) ;
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        layoutCircle = (LinearLayout) findViewById(R.id.friend_circle);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*
         初始化左侧滑菜单Item
         */
        loadLeftDatas();
        LeftDrawerAdapter leftAdapter = new LeftDrawerAdapter(mLeftDatas);
        mLeftRvView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mLeftRvView.setAdapter(leftAdapter);

        /*
        初始化朋友圈
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        topiclist.setLayoutManager(layoutManager);
        presenter.loadTopicList(MyApplication.getcurUser().getUid());


        //listener
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,PublishActivity.class);
                i.putExtra("flag",TYPE_IMAGE);
                MainActivity.this.startActivity(i);
            }
        });

        publishBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i=new Intent(MainActivity.this,PublishActivity.class);
                i.putExtra("flag",TYPE_TEXT);
                MainActivity.this.startActivity(i);
                return false;
            }
        });

        layoutCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,NearbyActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        leftAdapter.setListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position){
                    case 0:
                        Intent i=new Intent(MainActivity.this,SelectPhotoActivity.class);
                        MainActivity.this.startActivity(i);
                        break;
                    case 1:
                        i=new Intent(MainActivity.this,InformActivity.class);
                        MainActivity.this.startActivity(i);
                        break;
                    case 2:
                        break;
                    default:break;
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                presenter.loadTopicList(MyApplication.getcurUser().getUid());
            }
        });
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
    public void showProgressBar() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showTopicList(ArrayList<TopicBean> list) {
        adapter=new FriendCircleAdapter(MyApplication.getContext(),list);
        topiclist.setAdapter(adapter);
    }

}
