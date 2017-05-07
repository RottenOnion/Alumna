package com.example.alumna.utils.Image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;

import java.io.File;

/**
 * Created by Leebobo on 2017/5/7.
 */

public class ImageUtil {
    public static void LoadImageFromUrl(ImageView view,String url){
        Glide.with(MyApplication.getContext())
                .load(url)
                .placeholder(R.drawable.test_touxiang  )
                .error(R.drawable.test_touxiang)
                .into(view);
    }

    public static void LoadImageFromFile(ImageView view, File file){

    }

    public static void LoadImageFromRes(ImageView view,int resource){

    }
}
