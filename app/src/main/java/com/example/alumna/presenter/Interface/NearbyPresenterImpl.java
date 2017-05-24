package com.example.alumna.presenter.Interface;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface NearbyPresenterImpl {

    //加载附近的人
    void loadNearby(int uid,String location);

    //屏蔽
    void Block();

    //喜欢
    void Like();
}
