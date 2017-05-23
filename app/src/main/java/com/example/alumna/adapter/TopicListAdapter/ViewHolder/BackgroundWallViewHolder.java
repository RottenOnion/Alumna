package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.bean.UserBean;

/**
 * Created by Leebobo on 2017/5/10.
 */

public class BackgroundWallViewHolder extends RecyclerView.ViewHolder {

    private View view;
    public ImageView backgroundIv;
    public ImageView headTv;
    public TextView nameTv;

    public BackgroundWallViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        backgroundIv = (ImageView) view.findViewById(R.id.backgroundIv);
        headTv = (ImageView) view.findViewById(R.id.headIv);
        nameTv = (TextView) view.findViewById(R.id.nameTv);
    }

    public void init(UserBean curUser) {

//        Glide.with(MyApplication.getContext()).load(curUser.getHead()).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap inputBitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                headTv.setImageBitmap(inputBitmap);
//                /*裁剪图片，适应侧滑菜单大小*/
//
//                int width = inputBitmap.getWidth(),height = inputBitmap.getHeight();
//                Bitmap cropBitmap = Bitmap.createBitmap(inputBitmap,width/4,height/6,width/4*2,height/4*3);
//                /*模糊图片*/
//                Bitmap blurBitmap = ImageUtil.blurBitmap(MyApplication.getContext(), cropBitmap);
//                Drawable outputDrawable = new BitmapDrawable(MyApplication.getMyResource(),blurBitmap);
//                /*加入灰色遮罩层，避免图片过亮影响其他控件*/
//                outputDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
//                backgroundIv.setImageDrawable(outputDrawable);
//
//            }
//        });

        //设置头像
//        ImageUtil.displayImage(headTv, curUser.getHead());
        nameTv.setText(curUser.getUsername());
    }
}
