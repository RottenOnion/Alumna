package com.example.alumna.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.alumna.MyApplication;


/**
 * Created by Leebobo on 2017/5/11.
 * 高德地图的api：http://lbs.amap.com/api/android-location-sdk/guide/android-location/getlocation
 */

public class LocationUtil {

    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationClient  mLocationClient=null;

    private static LocationUtil instance;

    private LocationUtil(){
        mLocationOption = new AMapLocationClientOption();
        mLocationClient=new AMapLocationClient(MyApplication.getContext());
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：//该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(10000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    public static synchronized LocationUtil getInstance()
    {
        if (instance == null)
        {
            synchronized (LocationUtil.class)
            {
                if (instance==null){
                    instance = new LocationUtil();
                }
            }
        }
        return instance;
    }


    public void getLocation(final getLocationCallback callback) {

        callback.onStart();
        //启动定位
        mLocationClient.startLocation();

        AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        StringBuilder coordinate=new StringBuilder();
                        coordinate.append(amapLocation.getLatitude());//获取纬度
                        coordinate.append(",");
                        coordinate.append(amapLocation.getLongitude());//获取经度
                        String location=amapLocation.getCity()+amapLocation.getDistrict()+amapLocation.getStreet()+amapLocation.getStreetNum();
                        callback.onSuccess(location,coordinate.toString());
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                        callback.onFailure("定位失败");
                    }
                }
                callback.onFinish();
            }
        };
        mLocationClient.setLocationListener(mAMapLocationListener);
    }

    public interface getLocationCallback{
        void onStart();
        void onFinish();
        void onSuccess(String location,String coordinate);
        void onFailure(String result);
    }

    public void stop(){
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        //mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
