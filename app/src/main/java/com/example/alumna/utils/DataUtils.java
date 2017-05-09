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
//        for(int i=0;i<=5;++i){
//            UserBean user=new UserBean(i);
//            user.setUsername(i+"00");
//            list.add(user);
//        }
//
//        for(int i=0;i<4;++i){
//            CommentBean comment=new CommentBean(i);
//            comment.setUser(list.get(i));
//            commentlist.add(comment);
//        }
//        commentlist.get(0).setComment("那夜谁将酒喝掉");
//        commentlist.get(1).setComment("因此我讲得多了");
//        commentlist.get(2).setComment("然后你摇着我手拒绝我");
//        commentlist.get(3).setComment("动人像友情深了");
        curUser=new UserBean(1);
        curUser.setUsername("curUser");
    }

    public static ArrayList<UserBean> getList(){
        return list;
    }
    public static ArrayList<CommentBean>getCommentlist(){return commentlist;}
}
