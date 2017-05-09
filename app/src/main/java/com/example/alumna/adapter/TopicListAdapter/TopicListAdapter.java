package com.example.alumna.adapter.TopicListAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alumna.R;
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


public class TopicListAdapter extends BaseRecycleViewAdapter {

    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;
    private UserBean curUser= DataUtils.curUser;

    public TopicListAdapter(Context context,ArrayList<TopicBean> list){
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
            itemType = TopicListViewHolder.TYPE_TEXT;
        } else if (TopicBean.TYPE_IMAGE.equals(topic.getType())) {
            itemType = TopicListViewHolder.TYPE_IMAGE;
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_topic_item,parent, false);
        if (viewType== TopicListViewHolder.TYPE_TEXT) {
            holder= new TextViewHolder(view);
        }else if (viewType== TopicListViewHolder.TYPE_IMAGE){
           holder=new ImageViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,final int position) {
        final TopicListViewHolder holder = (TopicListViewHolder) viewHolder;

        holder.setData(list.get(position));
        //popupwindow
        holder.initPopupWindow(list.get(position).getTid());
        //Log.i(this.getClass().getName(),position+"------"+holder.viewType);

        switch (holder.viewType){
            case TopicListViewHolder.TYPE_IMAGE://图片
                Log.i(this.getClass().getName(),position+"------"+holder.viewType);

                //ImageUtil.LoadImageFromRes(((ImageViewHolder)holder).imageView,R.drawable.test_touxiang);
                break;
            default:
                break;
        }
    }


    public int getItemCount() {
        return list.size();
    }


}
