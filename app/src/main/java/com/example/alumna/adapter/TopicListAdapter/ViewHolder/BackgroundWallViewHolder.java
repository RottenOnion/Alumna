package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.Image.ImageUtil;

/**
 * Created by Leebobo on 2017/5/10.
 */

public class BackgroundWallViewHolder extends RecyclerView.ViewHolder{

    private View view;
    public ImageView backgroundIv;
    public ImageView headTv;
    public TextView nameTv;

    public BackgroundWallViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        backgroundIv=(ImageView)view.findViewById(R.id.backgroundIv);
        headTv=(ImageView)view.findViewById(R.id.headIv);
        nameTv=(TextView)view.findViewById(R.id.nameTv);
    }

    public void init(UserBean curUser){
        ImageUtil.displayImage(backgroundIv,
                "http://pic.qiantucdn.com/58pic/20/10/79/575539df7ebf3_1024.jpg");
        ImageUtil.displayImage(headTv,curUser.getHead());
        nameTv.setText(curUser.getUsername());
    }
}
