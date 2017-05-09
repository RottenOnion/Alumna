package com.example.alumna.adapter.TopicListAdapter;

/**
 * Created by Leebobo on 2017/5/9.
 */

public interface TopicListPresenetImpl {
    //为viewholder加载点赞列表
    public void loadLikeList();

    //为viewholder加载评论列表
    public void loadCommentList();

    //点赞
    public void setLike();

    //评论
    public void setComment();

    //查看用户
    public void getUserImfor();
}
