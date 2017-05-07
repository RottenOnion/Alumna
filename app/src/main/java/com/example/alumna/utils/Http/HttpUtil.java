package com.example.alumna.utils.Http;

import android.os.Handler;
import android.os.Looper;

import com.example.alumna.utils.ParseUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/28.
 */

public class HttpUtil {

    private Handler handler;
    private OkHttpClient client;
    //private static HttpUtil instance;

    public HttpUtil(){
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());

    }

//    public static synchronized HttpUtil getInstance()
//    {
//        if (instance == null)
//        {
//            synchronized (HttpUtil.class)
//            {
//                instance = new HttpUtil();
//            }
//        }
//        return instance;
//    }

    public void PostRequest(final String url, final Map<String, Object> params, final HttpRequestCallback callback){
        String data= ParseUtil.MapParseString(params);
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, data);

        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(getCallBack(callback));
    }

    private Callback getCallBack(final HttpRequestCallback callback){
        if (callback!=null){
            callback.onStart();
        }

        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                 System.out.println("failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    String result=response.body().string();
                    System.out.println(result);
                   onSuccessJsonStringMethod(result,callback);
                }
            }
        };
    }


    private void onSuccessJsonStringMethod(final String json, final HttpRequestCallback callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(json);
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

}