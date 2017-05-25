package com.example.alumna.view.Interface;


import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainViewImpl {

    void setUserInform(UserBean user);

    void showProgress();

    void hideProgress();

    void showFriendCircle(ArrayList<TopicBean> list);

    void showFriend(List<UserBean> userList);
}
