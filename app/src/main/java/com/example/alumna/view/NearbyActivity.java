package com.example.alumna.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.alumna.R;
import com.example.alumna.adapter.NearbyAdapter;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.NearbyPresenter;
import com.example.alumna.view.Interface.NearbyViewImpl;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyActivity extends Activity implements DialogInterface.OnClickListener, NearbyViewImpl {

    private NearbyPresenter presenter;
    private RecyclerView recyclerView;
    private List<UserBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);


        //findview
        initView();


        //set listener


        presenter = new NearbyPresenter(this);


        //load
        presenter.loadNearby();

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mDatas = new ArrayList<>();

        // TODO: 2017/5/15
        UserBean user1 = new UserBean(1);
        UserBean user2 = new UserBean(2);
        UserBean user3 = new UserBean(3);
        UserBean user4 = new UserBean(4);
        UserBean user5 = new UserBean(5);
        mDatas.add(user1);
        mDatas.add(user2);
        mDatas.add(user3);
        mDatas.add(user4);
        mDatas.add(user5);
        // TODO: 2017/5/15

        NearbyAdapter adapter = new NearbyAdapter(mDatas);
        recyclerView.setAdapter(adapter);
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
                    Toast.makeText(NearbyActivity.this, "SWIPED_LEFT", Toast.LENGTH_SHORT).show();
                } else if (direction == CardConfig.SWIPED_RIGHT) {
                    Toast.makeText(NearbyActivity.this, "SWIPED_RIGHT", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(NearbyActivity.this, "SwipedClear", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void showNearby(ArrayList<UserBean> list) {

    }
}
