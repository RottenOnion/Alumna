
 package com.example.alumna.model;

 import com.example.alumna.MyApplication;
 import com.example.alumna.model.Interface.PublishModelImpl;
 import com.example.alumna.utils.DataUtils;
 import com.example.alumna.utils.Http.HttpRequestCallback;
 import com.example.alumna.utils.Http.HttpUtil;
 import com.lzy.imagepicker.bean.ImageItem;

 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;

 /**
 * Created by Administrator on 2017/4/25.
 */

public class PublishModel implements PublishModelImpl {

    @Override
    public void uploadImage(List<ImageItem> imgs, HttpRequestCallback<String>callback) {
        HttpUtil upload=HttpUtil.getInstance();
        String url= DataUtils.BASEURL+DataUtils.SENDPIC;
        upload.UploadImage(url,imgs,callback);
    }

     @Override
     public void PublishTopic(String imfor, String location, int type, String imagepath, HttpRequestCallback<String> callback) {
         HashMap<String,Object>params=new HashMap<>();
         params.put("uid", MyApplication.getcurUser().getUid());
         params.put("location",location);
         params.put("type",""+type);
         params.put("imfor",imfor);
         params.put("time",""+new Date(System.currentTimeMillis()).getTime());
         params.put("image",imagepath);

         String url=DataUtils.BASEURL+DataUtils.NEWTOPIC;

         HttpUtil newtopic=HttpUtil.getInstance();
         newtopic.PostRequest(url,params,callback);
     }

}
