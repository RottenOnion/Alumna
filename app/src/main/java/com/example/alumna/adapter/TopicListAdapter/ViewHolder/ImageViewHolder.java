package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.utils.Image.ImageUtil;
import com.example.alumna.widgets.ImageShower;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leebobo on 2017/5/3.
 */

public class ImageViewHolder extends TopicListViewHolder {
    public ImageShower image_shower;
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
        ImageShower view=(ImageShower) subView.findViewById(R.id.image_shower);
        if (view!=null){
            this.image_shower=view;
            image_shower.setApdend(false);
        }
    }

    public void test(){
        ArrayList<ImageItem> list=new ArrayList<>();
        ImageItem item=new ImageItem();
        item.path="http://123.207.66.152:8080/picture/28c1946015eb41a6cadbdc26e396633b.jpg";
        for (int i=0;i<4;++i){
            item.name=""+i;
            list.add(item);
        }
        int column=3;
        if (list.size()==4){
            column=2;
        }else if (list.size()==1){
            column=1;
        }
        image_shower.setcolumn(column);
        image_shower.notifyDataSetChanged(list);
    }
}
