package com.example.alumna;

import android.app.Application;
import android.content.Context;

import com.example.alumna.widgets.GlideSelectImageLoader;
import com.lzy.imagepicker.ImagePicker;

/**
 * Created by Administrator on 2017/4/29.
 */

public class MyApplication extends Application{

    private static Context context;

    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = getApplicationContext();

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideSelectImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSelectLimit(9);
    }

    public static Context getContext(){
        return context;
    }

}
