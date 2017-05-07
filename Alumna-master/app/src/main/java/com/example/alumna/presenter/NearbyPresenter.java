package com.example.alumna.presenter;

import com.example.alumna.model.Interface.NearbyModelImpl;
import com.example.alumna.model.NearbyModel;
import com.example.alumna.presenter.Interface.NearbyPresenterImpl;
import com.example.alumna.view.Interface.NearbyViewImpl;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyPresenter implements NearbyPresenterImpl {
    private NearbyViewImpl nView;
    private NearbyModelImpl nModel;

    public NearbyPresenter(NearbyViewImpl view) {
        nView = view;
        nModel = new NearbyModel();
    }

    @Override
    public void loadNearby() {
        nView.showNearby(nModel.getNearby());
    }

    @Override
    public void Block() {

    }

    @Override
    public void Like() {

    }
}
