package com.example.alumna.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alumna.R;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.utils.DataUtils;

import java.util.ArrayList;



public class TopicListAdapter extends RecyclerView.Adapter<TextViewHolder>{

    private ArrayList<TopicBean> list=new ArrayList<TopicBean>() ;
    private Context context;
    private LayoutInflater inflater;

    public TopicListAdapter(Context context){
        this.context=context;
        list.add(new TopicBean(1));
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextViewHolder holder=null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_topic_item,parent, false);
//        if (viewType==TYPE_TEXT) {
//             holder= new TextViewHolder(view);
//        }else if (viewType==TYPE_IMAGE){
//           //holder=new ImageViewHolder(view);
//        }
        holder= new TextViewHolder(view);
        return holder;
    }



    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.commentListView.setList(DataUtils.getCommentlist());
        holder.praiseListView.setList(DataUtils.getList());
    }

    public int getItemCount() {
        return list.size();
    }
}
