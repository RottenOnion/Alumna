package com.example.alumna.widgets;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

/**
 * Created by py on 2017/5/11.
 */

public class GlideSelectImageLoader implements ImageLoader{

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(path)
                .override(width,height)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
