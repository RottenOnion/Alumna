package com.example.alumna.presenter;

import com.example.alumna.bean.NearbyUserBean;
import com.example.alumna.model.Interface.NearbyModelImpl;
import com.example.alumna.model.NearbyModel;
import com.example.alumna.presenter.Interface.NearbyPresenterImpl;
import com.example.alumna.presenter.listener.onNearbyListener;
import com.example.alumna.view.Interface.NearbyViewImpl;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyPresenter implements onNearbyListener,NearbyPresenterImpl {
    private NearbyViewImpl nView;
    private NearbyModelImpl nModel;

    public NearbyPresenter(NearbyViewImpl view) {
        nView = view;
        nModel = new NearbyModel(this);
    }

    @Override
    public void loadNearby(int uid,String location) {
        nModel.getNearby(uid,location);
    }

    @Override
    public void Block() {

    }

    @Override
    public void Like(int uid,int fid) {
        nModel.addFriend(uid, fid);
    }



    @Override
    public void onSuccess(List<NearbyUserBean> userList) {
        nView.showNearby(userList);
    }

    @Override
    public void onFail() {

    }
}
