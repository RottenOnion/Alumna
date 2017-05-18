package com.example.alumna.model.Interface;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

public interface MemberModelImpl {



    UserBean getUser(int id);

    ArrayList<TopicBean> getTopicList(int uid);

}
