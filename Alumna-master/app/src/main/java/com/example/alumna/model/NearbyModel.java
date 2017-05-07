package com.example.alumna.model;



import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.NearbyModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class NearbyModel implements NearbyModelImpl {
    @Override
    public ArrayList<UserBean> getNearby() {
        //向服务器请求获取附近的人列表
        return null;
    }
}
