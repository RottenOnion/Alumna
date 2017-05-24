package com.example.alumna.model;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.model.Interface.MemberCircleModelImpl;
import com.example.alumna.presenter.Interface.MemberCirclePresenterImpl;
import com.example.alumna.presenter.listener.OnMemberCircleListener;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/24.
 */

public class MemberCircleModel implements MemberCircleModelImpl{

    private OnMemberCircleListener mlistener;
    public MemberCircleModel(OnMemberCircleListener listener){
        mlistener=listener;
    }


}
