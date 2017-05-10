package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.utils.Image.ImageUtil;

/**
 * Created by Leebobo on 2017/5/3.
 */

public class ImageViewHolder extends TopicListViewHolder {
    public ImageView imageView;
    public ImageViewHolder(View itemView) {
        super(itemView, TYPE_IMAGE);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.viewstub_image);
        View subView=viewStub.inflate();
        ImageView imageView=(ImageView) subView.findViewById(R.id.imageview);
        if (imageView!=null){
            this.imageView=imageView;
        }
    }
}
