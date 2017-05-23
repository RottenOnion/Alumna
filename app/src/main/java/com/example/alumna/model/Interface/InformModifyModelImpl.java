package com.example.alumna.model.Interface;

import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface InformModifyModelImpl {

    /*获取用户信息*/
    void getUser(int uid);

    void InformModify(HashMap<String,Object> params);

    void UploadImage(List<ImageItem>imgs);
}
