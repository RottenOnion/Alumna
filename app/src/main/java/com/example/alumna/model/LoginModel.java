package com.example.alumna.model;

import android.os.Looper;

import com.example.alumna.model.Interface.LoginModelImpl;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.HttpConnectUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Leebobo on 2017/4/26.
 */

public class LoginModel implements LoginModelImpl{
    @Override
    public void Login(final Map<String, String> params) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                StringBuffer url= new StringBuffer(DataUtils.BASEURL);
                url.append(DataUtils.LOGININ);

                StringBuffer builder=new StringBuffer();

                if (null != params && !params.isEmpty()){
                    builder.append("{");
                    for (HashMap.Entry<String, String> entry : params.entrySet()){
                        builder.append("\"").append(entry.getKey()).append("\"").
                                append(":").
                                append("\"").append(URLEncoder.encode(entry.getValue())).append("\"").
                                append(",");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append("}");
                }

                HttpConnectUtil login=new HttpConnectUtil(url.toString(),builder.toString());
                System.out.print(builder);
                login.HttpPost();
            }
        }).start();

    }
}
