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
import com.example.alumna.widgets.SnsPopupWindow;

import java.util.ArrayList;

import static com.example.alumna.utils.ParseUtil.StringParseTime;


public class TopicListAdapter extends BaseRecycleViewAdapter {

    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;
    private TopicListPresenter present;
    private UserBean curUser= DataUtils.curUser;

    public TopicListAdapter(Context context,ArrayList<TopicBean> list){
        this.context=context;
        this.list=list;
        present=new TopicListPresenter(this);
    }

    public ArrayList<TopicBean> getTopicList(){
        return list;
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

        holder.itemView.setTag(position);
        holder.viewType=Integer.valueOf(list.get(position).getType());
        holder.imfor.setText(list.get(position).getImfor());
        holder.name.setText(list.get(position).getUsername());
        holder.location.setText(list.get(position).getLocation());
        holder.time.setText(StringParseTime(list.get(position).getTime()));

        holder.loadhead(list.get(position).getHead());

        holder.deleteBtn.setVisibility(View.GONE);

        //处理评论和点赞列表
        initCommentBody(position, holder);

        //popupwindow
        initPopupWindow(position, holder);
    }

    private void initPopupWindow(int position, final TopicListViewHolder holder) {
        holder.snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.popupWindow.showPopupWindow(view);
            }
        });
        holder.popupWindow.setItemClickListener(new PopupItemClickListener(position,list.get(position).getTid()));

    }

    private void initCommentBody(int position, TopicListViewHolder holder) {
        if(list.get(position).getCommentNum()>0||list.get(position).getLikeNum()>0){
            holder.line.setVisibility(View.VISIBLE);
            //处理点赞列表
            if (list.get(position).getLikeNum()>0){
                present.loadLikeList(holder,position);
            }else{
                holder.praiseListView.setVisibility(View.GONE);
            }

            if(list.get(position).getCommentNum()>0){
                present.loadCommentList(holder,position);
            }else {
                holder.commentListView.setVisibility(View.GONE);
            }
        }
    }

    public int getItemCount() {
        return list.size();
    }

    private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{
        //动态在列表中的位置
        private long lasttime = 0;
        private int tid;
        private int position;

        public PopupItemClickListener(final int position, final int tid){
            this.position = position;
            this.tid=tid;
        }

        @Override
        public void onItemClick(SnsPopupWindow.ActionItem actionitem, int position) {
            switch (position) {
                case 0://点赞、取消点赞
                    if(System.currentTimeMillis()- lasttime <500)//防止快速点击操作
                        return;
                    lasttime = System.currentTimeMillis();
                    present.setLike(curUser.getUid(),tid);
                    break;
                case 1://发布评论
                    present.setComment();
                    break;
                default:
                    break;
            }
        }
    }
}
