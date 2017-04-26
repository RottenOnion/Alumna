package com.example.alumna.model.Interface;

import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

public interface MemberModelImpl {

    void setUid(int id);

    void setUserName(String name);

    void setHead(String head);

    void setSex(String sex);

    void setLocation(String location);

    void setPhone(String phone);

    UserBean getUser(int id);

    ArrayList<TopicBean> getTopicList(int uid);

}
