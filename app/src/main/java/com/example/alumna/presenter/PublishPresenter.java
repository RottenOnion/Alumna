package com.example.alumna.presenter;

import android.util.Log;

import com.example.alumna.model.Interface.PublishModelImpl;
import com.example.alumna.model.PublishModel;
import com.example.alumna.presenter.Interface.PublishPresenterImpl;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.LocationUtil;
import com.example.alumna.view.Interface.PublishViewImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/4/25.
 */

public class PublishPresenter implements PublishPresenterImpl {
    private PublishViewImpl pView;
    private PublishModelImpl pModel;
    private final static int TYPE_TEXT=1;
    private final static int TYPE_IMAGE=2;

    public PublishPresenter(PublishViewImpl view) {
        pView = view;
        pModel = new PublishModel();

    }

    @Override
    public void publishImageType(final String imfor,final String location) {
        //先发图片，获取url后再发动态
        List<ImageItem>imgs=pView.getSelectImg();
        pModel.uploadImage(imgs, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                Log.i(this.getClass().getName(),"图片正上传..");
                pView.showprogressbar("图片正上传..");
            }

            @Override
            public void onFinish() {
                Log.i(this.getClass().getName(),"图片已上传");

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                String imageurl=jsonObject.get("url").getAsString();
                Log.i("image",imageurl);
                sendTopic(imfor,location,TYPE_IMAGE,imageurl);
            }

            @Override
            public void onFailure(Call call) {
                Log.i(this.getClass().getName(),"图片上传失败..");
            }
        });
    }

    @Override
    public void publish(int type) {
        final String imfor = pView.getEditViewText();
        final String location = pView.getLocation();
        if (type == TYPE_IMAGE) {
            publishImageType(imfor, location);
        } else if (type == TYPE_TEXT) {
            sendTopic(imfor, location, TYPE_TEXT, "");
        }
    }

    @Override
    public void getLocation(){
        LocationUtil.getInstance().getLocation(new LocationUtil.getLocationCallback() {
            @Override
            public void onStart() {
                pView.setLocationText("定位中...","");
            }

            @Override
            public void onFinish() {
                LocationUtil.getInstance().stop();
            }

            @Override
            public void onSuccess(String location,String coordinate) {
                pView.setLocationText(location,coordinate);
            }

            @Override
            public void onFailure(String result) {
                pView.setLocationText(result,"");
            }
        });
    }

    private void sendTopic(String imfor, String location, int type, String imageUrl){
        pModel.PublishTopic(imfor, location, type, imageUrl, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                Log.i(this.getClass().getName(),"topic正上传..");
                pView.showprogressbar("发送中..");
            }

            @Override
            public void onFinish() {
                Log.i(this.getClass().getName(),"topic已经上传..");
                pView.hideprogressbar();
                pView.FinishAcitvity();
            }

            @Override
            public void onResponse(String s) {
                Log.i("settopic",s);
                onFinish();
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }
}
