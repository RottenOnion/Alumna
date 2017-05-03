package com.example.alumna.view.Adapter;

import android.view.View;
import android.view.ViewStub;

import com.example.alumna.R;

/**
 * Created by Leebobo on 2017/5/3.
 */

public class ImageViewHolder extends TopicListViewHolder {
    public ImageViewHolder(View itemView) {
        super(itemView, TYPE_IMAGE);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }

    }
}
