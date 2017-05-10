package com.example.alumna.DBHelper;

import android.util.Log;

import com.example.alumna.MyApplication;
import com.example.alumna.bean.TopicBean;
import com.example.greendao.DaoMaster;
import com.example.greendao.DaoSession;
import com.example.greendao.TopicBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leebobo on 2017/5/10.
 */



public class DateBaseHelper {
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private TopicBeanDao topicBeanDao;
    private static DateBaseHelper instance;

    private DateBaseHelper(){
        devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "alumna.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        topicBeanDao=daoSession.getTopicBeanDao();

    }

    public static synchronized DateBaseHelper getInstance(){
        if (instance == null)
        {
            synchronized (DateBaseHelper.class)
            {
                if (instance==null){
                    instance = new DateBaseHelper();
                }
            }
        }
        return instance;
    }

//    public void openDb(){
//        devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "topic.db", null);
//        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
//        daoSession = daoMaster.newSession();
//    }

    public void insert(TopicBean topic){
        topicBeanDao.insert(topic);
    }

    public void insertAll(ArrayList<TopicBean> topiclist){
        for(int i=0;i<topiclist.size();++i){
            this.insert(topiclist.get(i));
        }
    }

    public void delete(TopicBean topic){
        topicBeanDao.delete(topic);
    }

    public void update(TopicBean topic){
        topicBeanDao.update(topic);
    }

    public List<TopicBean> queryAll() {
        return topicBeanDao.queryBuilder().list();
    }


}
