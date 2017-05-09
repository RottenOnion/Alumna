package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

/**
 * Created by Leebobo on 2017/5/9.
 */

public interface ViewHolderPresenterImpl {

    //为viewholder加载点赞列表
    void loadLikeList(final int tid);

    //为viewholder加载评论列表
    void loadCommentList(final int tid);

    //点赞
    void setLike(final int uid,final int tid);

    //评论
    void setComment();

    //查看用户
    void getUserImfor(final int uid);
}
