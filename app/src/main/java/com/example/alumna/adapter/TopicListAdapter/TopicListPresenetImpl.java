package com.example.alumna.adapter.TopicListAdapter;

import com.example.alumna.adapter.TopicListAdapter.ViewHolder.TopicListViewHolder;

/**
 * Created by Leebobo on 2017/5/9.
 */

public interface TopicListPresenetImpl {
    //为viewholder加载点赞列表
    void loadLikeList(final TopicListViewHolder holder, final int position);

    //为viewholder加载评论列表
    void loadCommentList(final TopicListViewHolder holder, final int position);

    //点赞
    void setLike(final int uid,final int tid);

    //评论
    void setComment();

    //查看用户
    void getUserImfor(int uid);
}
