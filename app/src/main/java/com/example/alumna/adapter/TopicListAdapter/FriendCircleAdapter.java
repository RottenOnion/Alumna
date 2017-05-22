package com.example.alumna.adapter.TopicListAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.BackgroundWallViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.ImageViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.TextViewHolder;
import com.example.alumna.adapter.TopicListAdapter.ViewHolder.TopicListViewHolder;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Image.ImageUtil;
import com.example.alumna.widgets.SnsPopupWindow;

import java.util.ArrayList;

import static com.example.alumna.utils.ParseUtil.StringParseTime;


public class FriendCircleAdapter extends BaseRecycleViewAdapter {

    private static final int TYPE_BGWALL=0;
    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;
    private UserBean curUser= MyApplication.getcurUser();

    public FriendCircleAdapter(Context context, ArrayList<TopicBean> list){
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
            itemType = TopicListViewHolder.TYPE_TEXT;
        } else if (TopicBean.TYPE_IMAGE.equals(topic.getType())) {
            itemType = TopicListViewHolder.TYPE_IMAGE;
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
            holder=new BackgroundWallViewHolder(view);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_topic_item,parent, false);
            if (viewType== TopicListViewHolder.TYPE_TEXT) {
                //文本消息
                holder= new TextViewHolder(view);
            }else if (viewType== TopicListViewHolder.TYPE_IMAGE){
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
            holder.init(curUser);
            return;
        }

        final TopicListViewHolder holder = (TopicListViewHolder) viewHolder;
        //注意减去Background的位置
        holder.setData(list.get(position-1));
        //popupwindow
        holder.initPopupWindow(list.get(position-1).getTid());

        switch (holder.viewType){
            case TopicListViewHolder.TYPE_IMAGE://图片
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
