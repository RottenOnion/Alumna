package com.example.alumna.utils.Image;

import android.app.Activity;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;

/**
 * Created by Leebobo on 2017/5/12.
 */

public class GlideImageLoader implements ImageLoader{
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        ImageUtil.displayImage(imageView,path);
    }

    @Override
    public void clearMemoryCache() {

    }
}
