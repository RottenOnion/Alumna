package com.example.alumna.adapter.TopicListAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alumna.R;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.BaseViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.ImageViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.TextViewHolder;
import com.example.alumna.bean.TopicBean;

import java.util.ArrayList;


public class FriendCircleAdapter extends BaseRecycleViewAdapter {

    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;

    public FriendCircleAdapter(final Context context, ArrayList<TopicBean> list){
        this.context=context;
        this.list=list;

    }

    @Override
    //获取topic的类型，按照不同的type来初始化
    //如果不给viewtype初始化会报空指针
    public int getItemViewType(int position) {
        int itemType = 0;
        TopicBean topic = list.get(position);
        if (TopicBean.TYPE_TEXT.equals(topic.getType())) {
            itemType = BaseViewHolder.TYPE_TEXT;
        } else if (TopicBean.TYPE_IMAGE.equals(topic.getType())) {
            itemType = BaseViewHolder.TYPE_IMAGE;
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view ;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_topic_item,parent, false);
        if (viewType== BaseViewHolder.TYPE_TEXT) {
            //文本消息
            holder= new TextViewHolder(view);
        }else if (viewType== BaseViewHolder.TYPE_IMAGE){
            //图片消息
            holder=new ImageViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,final int position) {

        final BaseViewHolder holder = (BaseViewHolder) viewHolder;
        holder.setData(list.get(position));
        //popupwindow
        holder.initPopupWindow(list.get(position).getTid());

        switch (holder.viewType){
            case BaseViewHolder.TYPE_IMAGE://图片
                ((ImageViewHolder)holder).show(list.get(position));
                break;
        }
    }


    public int getItemCount() {
        return list.size();
    }
}
