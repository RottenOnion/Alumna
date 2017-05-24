
 package com.example.alumna.model;

 import android.util.Log;

 import com.example.alumna.MyApplication;
 import com.example.alumna.model.Interface.PublishModelImpl;
 import com.example.alumna.presenter.listener.OnPublishListener;
 import com.example.alumna.utils.DataUtils;
 import com.example.alumna.utils.Http.HttpRequestCallback;
 import com.example.alumna.utils.Http.HttpUtil;
 import com.google.gson.JsonObject;
 import com.google.gson.JsonParser;
 import com.lzy.imagepicker.bean.ImageItem;

 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;

 import okhttp3.Call;

 /**
 * Created by Administrator on 2017/4/25.
 */

public class PublishModel implements PublishModelImpl {

     private OnPublishListener mlistener;
     public PublishModel(OnPublishListener listener){
         mlistener=listener;
     }
    @Override
    public void uploadImage(List<ImageItem> imgs) {
        String url= DataUtils.BASEURL+DataUtils.SENDPIC;

        HttpUtil.getInstance().UploadImage(url,imgs,new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                Log.i(this.getClass().getName(),"图片正上传..");
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                String imageurl=jsonObject.get("url").getAsString();
                Log.i("image",imageurl);
                mlistener.OnImageSuccess(imageurl);
            }

            @Override
            public void onFailure(Call call) {
                mlistener.OnFailure();
            }
        });
    }

     @Override
     public void PublishTopic(HashMap<String,Object>params) {
         String url=DataUtils.BASEURL+DataUtils.NEWTOPIC;

         HttpUtil.getInstance().PostRequest(url,params,new HttpRequestCallback<String>() {
             @Override
             public void onStart() {
                 mlistener.OnPublishStart();
             }

             @Override
             public void onFinish() {
             }

             @Override
             public void onResponse(String s) {
                 mlistener.OnPublishSuccess();
             }

             @Override
             public void onFailure(Call call) {

             }
         });
     }

}
