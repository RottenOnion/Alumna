package com.example.alumna.adapter.TopicListAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.BackgroundWallViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.ImageViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.TextViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.BaseViewHolder;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;

import java.util.ArrayList;


public class FriendCircleAdapter extends BaseRecycleViewAdapter {

    private static final int TYPE_BGWALL=0;
    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;
    private UserBean curUser= MyApplication.getcurUser();
    private static Drawable backgroundDra;
    private static Bitmap headBitmap;

    public FriendCircleAdapter(final Context context, ArrayList<TopicBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    //获取topic的类型，按照不同的type来初始化
    //如果不给viewtype初始化会报空指针
    public int getItemViewType(int position) {
        if (position==0)return TYPE_BGWALL;
        int itemType = 0;
        TopicBean topic = list.get(position-1);
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
        if (viewType==TYPE_BGWALL){
            //背景墙
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.background_wall_item,parent, false);
            holder=new BackgroundWallViewHolder(view,context);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_topic_item,parent, false);
            if (viewType== BaseViewHolder.TYPE_TEXT) {
                //文本消息
                holder= new TextViewHolder(view);
            }else if (viewType== BaseViewHolder.TYPE_IMAGE){
                //图片消息
                holder=new ImageViewHolder(view);
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,final int position) {
        if(position==TYPE_BGWALL){
            BackgroundWallViewHolder holder=(BackgroundWallViewHolder)viewHolder;
            holder.nameTv.setText(curUser.getUsername());
            Glide.with(context).load(curUser.getHead()).into(holder.headTv);
            Glide.with(context).load(curUser.getBackground()).into(holder.backgroundIv);
            return;
        }

        final BaseViewHolder holder = (BaseViewHolder) viewHolder;
        //注意减去Background的位置
        holder.setData(list.get(position-1));
        //popupwindow
        holder.initPopupWindow(list.get(position-1).getTid());

        switch (holder.viewType){
            case BaseViewHolder.TYPE_IMAGE://图片
                ((ImageViewHolder)holder).show(list.get(position-1));
                break;
            default:
                break;
        }
    }


    public int getItemCount() {
        return list.size()+1;
    }




}
