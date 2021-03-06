package com.example.alumna.view.Interface;


import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

public interface MemberViewImpl {

    void showUserInform(UserBean user);

    void showTopicList(ArrayList<TopicBean> list);

    void showProgress();

    void hideProgress();
}
