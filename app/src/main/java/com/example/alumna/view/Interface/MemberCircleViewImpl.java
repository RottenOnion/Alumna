package com.example.alumna.view.Interface;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/24.
 */

public interface MemberCircleViewImpl {
    void showCircle(ArrayList<TopicBean> list);
    void showUser(UserBean user) ;
}
