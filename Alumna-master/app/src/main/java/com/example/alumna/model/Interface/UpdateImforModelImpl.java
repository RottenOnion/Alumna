package com.example.alumna.model.Interface;

import com.example.alumna.bean.UserBean;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface UpdateImforModelImpl {

    /*获取用户信息*/
    UserBean getUser(int uid);

    void setUser(UserBean user);
}
