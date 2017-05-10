package com.example.alumna.utils;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leebobo on 2017/4/26.
 */

public class DataUtils {

   public static ArrayList<UserBean> list=new ArrayList<UserBean>();
    public static ArrayList<CommentBean> commentlist=new ArrayList<>();

    public static String BASEURL="http://123.207.66.152:8080/Myservlet/";
    public static String LOGININ="login";
    public static String INIT="init";
    public static String GETTOPIC="gettopic";
    public static String GETCOMMENT="getcomment";
    public static String GETLIKE="getlike";
    public static String SETLIKE="setlike";
    public static String SETCOMMENT="setcomment";
    public static String GETUSER="getuser";



    public static UserBean curUser;


    static {

        curUser=new UserBean(1);
        curUser.setUsername("lollipop");
        curUser.setHead("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=966093302,1751994111&fm=58");
    }

    public static ArrayList<UserBean> getList(){
        return list;
    }
    public static ArrayList<CommentBean>getCommentlist(){return commentlist;}
}
