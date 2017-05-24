package com.example.alumna.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.alumna.R;
import com.example.alumna.adapter.NearbyAdapter;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.NearbyPresenter;
import com.example.alumna.view.Interface.NearbyViewImpl;
import com.mingle.widget.LoadingView;

import java.util.ArrayList;
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
    private List<UserBean> mDatas;
    private NearbyAdapter mNearbyAdapter;
    private LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        presenter = new NearbyPresenter(this);

        mDatas = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadingView = (LoadingView) findViewById(R.id.loading_view);
        loadingView.setVisibility(View.VISIBLE);
        loadingView.setLoadingText("搜寻附近的校友中...");


        //load
        presenter.loadNearby(6,"123");

        //set listener


        presenter = new NearbyPresenter(this);




    }




    @Override
    public void showNearby(List<UserBean> list) {
        loadingView.setVisibility(View.GONE);
        if (mNearbyAdapter == null) {
            mNearbyAdapter = new NearbyAdapter(list,this);
            recyclerView.setAdapter(mNearbyAdapter);
            CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), mDatas);
            ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
            CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
            recyclerView.setLayoutManager(cardLayoutManager);
            touchHelper.attachToRecyclerView(recyclerView);
            cardCallback.setOnSwipedListener(new OnSwipeListener() {
                @Override
                public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                    if (direction == CardConfig.SWIPED_LEFT) {

                    } else if (direction == CardConfig.SWIPED_RIGHT) {

                    }
                }

                @Override
                public void onSwipedClear() {
                    Toast.makeText(NearbyActivity.this, "SwipedClear", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mNearbyAdapter.setDatas(list);
            mNearbyAdapter.notifyDataSetChanged();
        }
    }
}
