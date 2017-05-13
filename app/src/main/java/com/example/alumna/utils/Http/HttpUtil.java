package com.example.alumna.utils.Http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.alumna.utils.ParseUtil;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private static HttpUtil instance;

    private HttpUtil(){
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }
    /*
    单例模式，确保只有一个线程访问网络
     */
    public static synchronized HttpUtil getInstance()
    {
        if (instance == null)
        {
            synchronized (HttpUtil.class)
            {
                if (instance==null){
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }

    public void PostRequest(final String url, final Map<String, Object> params, final HttpRequestCallback callback){
        String data= ParseUtil.MapParseString(params);
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, data);

        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(getCallBack(callback));
    }

    public void UploadImage(final String url, final List<ImageItem> imgs,final HttpRequestCallback callback){
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (ImageItem img:imgs){
            File file=new File(img.path);
            if (file!=null){
                builder.addFormDataPart("img", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
            }
        }

        MultipartBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(getCallBack(callback));
    }

    private Callback getCallBack(final HttpRequestCallback callback){
        if (callback!=null){
            callback.onStart();
        }

        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(this.getClass().getName(),"request failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    Log.i(this.getClass().getName(),"request successful");
                    String result=response.body().string();
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