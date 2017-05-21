package com.example.alumna.presenter.Interface;

import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by Leebobo on 2017/5/19.
 */

public interface OnMainListener {
    void TopicSuccess(ArrayList<TopicBean> list);
    void LikeListSuccess(ArrayList<UserBean>list);
    void CommentListSuccess(ArrayList<CommentBean>list);
    void onError();
}
