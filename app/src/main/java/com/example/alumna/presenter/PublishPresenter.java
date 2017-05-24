package com.example.alumna.presenter;

import com.example.alumna.MyApplication;
import com.example.alumna.model.Interface.PublishModelImpl;
import com.example.alumna.model.PublishModel;
import com.example.alumna.presenter.Interface.PublishPresenterImpl;
import com.example.alumna.presenter.listener.OnPublishListener;
import com.example.alumna.utils.LocationUtil;
import com.example.alumna.view.Interface.PublishViewImpl;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/4/25.
 */

public class PublishPresenter implements PublishPresenterImpl,OnPublishListener {
    private PublishViewImpl pView;
    private PublishModelImpl pModel;
    private final static int TYPE_TEXT=1;
    private final static int TYPE_IMAGE=2;

    public PublishPresenter(PublishViewImpl view) {
        pView = view;
        pModel = new PublishModel(this);

    }

    public void UploadImage() {
        pView.showProgressbar("图片上传中...");
        //先发图片，获取url后再发动态
        List<ImageItem>imgs=pView.getSelectImg();
        pModel.uploadImage(imgs);
    }


    @Override
    public void publish(int type) {
        if (type == TYPE_IMAGE) {
            UploadImage();
        } else if (type == TYPE_TEXT) {
            sendTopic();
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

    private void sendTopic() {
        HashMap<String,Object> params=new HashMap<>();
        params.put("uid", MyApplication.getcurUser().getUid());
        params.put("location",pView.getLocation());
        params.put("type",""+TYPE_TEXT);
        params.put("image","");
        params.put("imfor",pView.getEditViewText().toString());
        params.put("time",""+new Date(System.currentTimeMillis()).getTime());
        pModel.PublishTopic(params);
    }

    @Override
    public void OnImageSuccess(String url) {
        HashMap<String,Object> params=new HashMap<>();
        params.put("image",url);
        params.put("uid", MyApplication.getcurUser().getUid());
        params.put("location",pView.getLocation());
        params.put("type",""+ TYPE_IMAGE);
        params.put("imfor",pView.getEditViewText().toString());
        params.put("time",""+new Date(System.currentTimeMillis()).getTime());
        pModel.PublishTopic(params);
    }

    @Override
    public void OnPublishSuccess() {
        pView.hideProgressbar();
        pView.FinishAcitvity();
    }

    @Override
    public void OnError() {

    }

    @Override
    public void OnFailure() {
        pView.hideProgressbar();
    }

    @Override
    public void OnPublishStart() {
        pView.showProgressbar("动态发送中...");
    }
}
