package com.example.alumna.presenter.Interface;

import com.example.alumna.bean.UserBean;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface UpdateImforPresenterImpl {
    /*初始化用户信息*/
    void loadImfor(int uid);

    /*更新用户信息*/
    void UpdateImfor(UserBean user);
}
