package com.example.alumna.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.utils.DensityUtil;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/14.
 */

public class ImageListView extends GridLayout{

    private ArrayList<ImageItem> imgs;

    private Context context;
    private static final int DEFAULT_COW_COUNT =4;//默认每行4列
    private static final boolean DEFAULT_APDEND=true;//默认追加一张图片
    private static int DEFUALT_SPACING = 10;//默认间隔10px
    private static int MAX_WIDTH=0;

    private int cowCount=DEFAULT_COW_COUNT;//列数
    private boolean isApdend=DEFAULT_APDEND;//默认追加一张图片
    private int spacing = DEFUALT_SPACING;//图片间默认间隔10px

    private OnItemClickListener onItemClickListener;
    public ImageListView(Context context) {
        super(context);
        this.context=context;
    }

    public ImageListView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSpacing(int spacing){
        this.spacing=spacing;
    }

    public void setApdend(boolean apdend) {
        isApdend = apdend;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void notifyDataSetChanged(ArrayList<ImageItem> list){
        if(list!=null&&list.size()>0){
            imgs=list;
            //这里应该获取父容器的宽度，暂时不做
            MAX_WIDTH=DensityUtil.dip2px(MyApplication.getContext(),300);
            showImageList();
        }
    }

    private void showImageList(){
        removeAllViews();
        setColumnCount(cowCount);
        for (int i=0;i<imgs.size();++i){
            LayoutParams params= new LayoutParams();
            params.width=(MAX_WIDTH-cowCount*spacing)/cowCount;
            params.height=params.width;
            params.setMargins(spacing,spacing,spacing,spacing);
            addView(createImageView(i),params);
        }
        //最后一个追加一个选择器
        if (isApdend){
            LayoutParams params= new LayoutParams();
            params.width=(MAX_WIDTH-cowCount*spacing)/cowCount;
            params.height=params.width;
            params.setMargins(spacing,spacing,spacing,spacing);
            addView(createImageView(-1),params);
        }
    }


    private ImageView createImageView(int position){
        ImageView imageView=new ImageView(getContext());
        if (position==-1){
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(getContext()).load(R.drawable.ic_add).into(imageView);
            return imageView;
        }else {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//截取中间显示
            imageView.setId(imgs.get(position).name.hashCode());
            //imageView.setOnClickListener(OnItemClickListener);
            Glide.with(getContext()).load(imgs.get(position).path).into(imageView);
            return imageView;
        }
    }



    public interface OnItemClickListener {
        public void Click(View view,int position);
    }
}
