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
    public static void displayImage(ImageView view, String path){
        Glide.with(MyApplication.getContext())
                .load(path)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(view);
    }
}
