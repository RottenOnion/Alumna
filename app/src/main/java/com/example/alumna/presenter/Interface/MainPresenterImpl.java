package com.example.alumna.presenter.Interface;


import com.example.alumna.bean.TopicBean;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface MainPresenterImpl {

    void loadCurUser(final int uid);

    void loadTopicList(int uid);

    void loadLocation();

    void loadFriendList(int uid);

    void uploadBackground(final int uid,final ArrayList<ImageItem> img);

}
