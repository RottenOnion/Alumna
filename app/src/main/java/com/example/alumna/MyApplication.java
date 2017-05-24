package com.example.alumna;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import com.example.alumna.SharePreference.UserInforSP;
import com.example.alumna.bean.UserBean;
import com.example.alumna.widgets.GlideSelectImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/29.
 */

public class MyApplication extends Application{

    private static Context context;
    private static UserBean curUser;
    private static Resources resources;


    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = getApplicationContext();
        resources = getResources();

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideSelectImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSelectLimit(9);

        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        UserStrategy strategy =new UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, "a9f38a53e7", true);
    }

    public static Context getContext(){
        return context;
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static UserBean getcurUser(){
        if (curUser==null){
            //读取本地用户信息，如果没有，跳去登录
            //UserInforSP load=new UserInforSP(context);
            //curUser=load.loadCurUserData();
        }
        return curUser;
    }

    public static void setCurUser(UserBean user){
        curUser=user;
        //保存到本地
//        UserInforSP save=new UserInforSP(context);
//        save.saveCurUserData(user);
    }

    public static Resources getMyResource() {
        return resources;
    }
}
