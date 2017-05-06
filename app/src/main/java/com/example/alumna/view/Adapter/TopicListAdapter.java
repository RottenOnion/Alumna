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

import static com.example.alumna.utils.ParseUtil.StringParseTime;


public class TopicListAdapter extends RecyclerView.Adapter<TextViewHolder>{

    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;

    public TopicListAdapter(Context context,ArrayList<TopicBean> list){
        this.context=context;
        this.list=list;
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
        holder.viewType=Integer.valueOf(list.get(position).getType());
        holder.imfor.setText(list.get(position).getImfor());
        holder.name.setText(list.get(position).getUsername());
        holder.location.setText(list.get(position).getLocation());
        holder.time.setText(StringParseTime(list.get(position).getTime()));
        holder.commentListView.setList(DataUtils.getCommentlist());
        holder.praiseListView.setList(DataUtils.getList());
        holder.deleteBtn.setVisibility(View.GONE);
    }

    public int getItemCount() {
        return list.size();
    }
}
