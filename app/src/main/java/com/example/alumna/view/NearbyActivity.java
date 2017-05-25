package com.example.alumna.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.adapter.NearbyAdapter;
import com.example.alumna.bean.NearbyUserBean;
import com.example.alumna.presenter.NearbyPresenter;
import com.example.alumna.view.Interface.NearbyViewImpl;
import com.mingle.widget.LoadingView;

import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyActivity extends Activity implements NearbyViewImpl {

    private NearbyPresenter presenter;
    private RecyclerView recyclerView;
    private NearbyAdapter mNearbyAdapter;
    private LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        presenter = new NearbyPresenter(this);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadingView = (LoadingView) findViewById(R.id.loading_view);
        loadingView.setVisibility(View.VISIBLE);
        loadingView.setLoadingText("搜寻附近的校友中...");

        presenter = new NearbyPresenter(this);
        //load
        Log.d("cao","id" + MyApplication.getcurUser().getUid());
        Log.d("cao","location" + MyApplication.getcurUser().getLocation());
        if (MyApplication.getcurUser().getLocation() == null || MyApplication.getcurUser().getLocation().equals("")) {
            Toast.makeText(this, "未获取到您的当前位置，使用默认位置搜寻...", Toast.LENGTH_SHORT).show();
            presenter.loadNearby(MyApplication.getcurUser().getUid(), "23.051482,113.400643");
        } else {
            presenter.loadNearby(MyApplication.getcurUser().getUid(), MyApplication.getcurUser().getLocation());
        }



        //set listener

    }




    @Override
    public void showNearby(List<NearbyUserBean> list) {



        loadingView.setVisibility(View.GONE);

        mNearbyAdapter = new NearbyAdapter(list,this);
        recyclerView.setAdapter(mNearbyAdapter);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
        cardCallback.setOnSwipedListener(new OnSwipeListener<NearbyUserBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, NearbyUserBean user, int direction) {
                if (direction == CardConfig.SWIPED_RIGHT) {
                    presenter.Like(MyApplication.getcurUser().getUid(),user.getUid());
                    Toast.makeText(NearbyActivity.this, "成功将" + user.getUsername() + "加为好友!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(NearbyActivity.this, "已无附近的校友!", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
