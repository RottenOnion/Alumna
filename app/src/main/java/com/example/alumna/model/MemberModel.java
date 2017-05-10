package com.example.alumna.model;


import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.model.Interface.MemberModelImpl;

import java.util.ArrayList;

public class MemberModel implements MemberModelImpl {

    @Override
    public void setUid(int id) {

    }

    @Override
    public void setHead(String head) {

    }

    @Override
    public void setUserName(String name) {

    }

    @Override
    public void setSex(String sex) {

    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public UserBean getUser(int id) {

        //http���󣬷���һ��json����������һ��user����
        UserBean test = new UserBean(id);
        test.setLocation("scut");
        test.setSex("male");
        test.setUsername("testfor1");
        test.setPhone("12312313");

        return test;
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
