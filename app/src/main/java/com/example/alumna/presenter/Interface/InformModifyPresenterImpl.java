package com.example.alumna.presenter.Interface;

import com.example.alumna.bean.UserBean;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface InformModifyPresenterImpl {
    /*初始化用户信息*/
    void loadImfor(int uid);

    /*更新用户信息*/
    void UpdateImfor(int uid);

    /*后台传输图片*/
    void UploadImage(List<ImageItem> imgs);
}
