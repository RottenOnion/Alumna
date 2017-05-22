package com.example.alumna.SharePreference;

import android.content.Context;

import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.SharedPreferencesUtil;

/**
 * Created by Leebobo on 2017/5/14.
 */

public class UserInforSP {
    final static String FILE_NAME="user_data";
    Context context;

    public UserInforSP(Context context){
        this.context=context;
    }

    public void saveCurUserData(UserBean user){
        SharedPreferencesUtil sp=SharedPreferencesUtil.getInstance(context,FILE_NAME);
        sp.saveData("uid",user.getUid());
        sp.saveData("head",user.getHead());
        sp.saveData("username",user.getUsername());
        sp.saveData("sex",user.getSex());
        sp.saveData("location",user.getLocation());
        sp.saveData("phone",user.getPhone());
        sp=null;
    }

    public UserBean loadCurUserData(){
        SharedPreferencesUtil sp=SharedPreferencesUtil.getInstance(context,FILE_NAME);
        UserBean user=new UserBean(0);
        user.setUid((int)sp.getData("uid",0));
        user.setHead((String)sp.getData("head",""));
        user.setSex((int)sp.getData("sex",""));
        user.setUsername((String)sp.getData("username",""));
        user.setLocation((String)sp.getData("location",""));
        user.setPhone((String)sp.getData("phone",""));
        return user;
    }
}
