package com.example.alumna;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/4/29.
 */

public class MyApplication extends Application{

    private static Context context;

    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
