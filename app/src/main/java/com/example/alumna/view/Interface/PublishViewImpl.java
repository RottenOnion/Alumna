package com.example.alumna.view.Interface;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public interface PublishViewImpl {
    void setLocationText(String location,String coordinate);

    String getLocation();

    String getEditViewText();

    List<ImageItem> getSelectImg();

    void showprogressbar(String message);

    void hideprogressbar();

    void FinishAcitvity();
}
