package com.example.alumna.model;


import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberModelImpl;
import com.example.alumna.presenter.Interface.OnMemberListener;
import com.example.alumna.utils.Http.HttpRequestCallback;

import java.util.ArrayList;

import okhttp3.Call;

public class MemberModel implements MemberModelImpl {

    OnMemberListener mListener;

    public MemberModel(OnMemberListener memberListener) {
        this.mListener = memberListener;
    }

    @Override
    public UserBean getUser(int id) {

        new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String s) {
                mListener.onUserSuccess(new UserBean(123));
            }

            @Override
            public void onFailure(Call call) {

            }
        };

        return null;
    }

    public ArrayList<TopicBean> getTopicList(int uid) {

        //http���󣬷���һ��json����������һ��topic�б�
        ArrayList<TopicBean> list = new ArrayList<TopicBean>();

        int size = 10;
        for (int i = 0; i < size; ++i) {
            TopicBean temp = new TopicBean();
            temp.setInfor("" + i);
            list.add(temp);
        }

        return list;
    }

}
