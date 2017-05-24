package com.example.alumna.model.Interface;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface PublishModelImpl {

    void uploadImage(List<ImageItem> imgs);

    void PublishTopic(HashMap<String,Object> params);
}
