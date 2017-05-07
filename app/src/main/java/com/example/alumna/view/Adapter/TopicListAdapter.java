package com.example.alumna.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.widgets.CommentListView;
import com.example.alumna.widgets.PraiseListView;

import java.util.ArrayList;
import java.util.List;

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

        holder.itemView.setTag(position);
        holder.viewType=Integer.valueOf(list.get(position).getType());
        holder.imfor.setText(list.get(position).getImfor());
        holder.name.setText(list.get(position).getUsername());
        holder.location.setText(list.get(position).getLocation());
        holder.time.setText(StringParseTime(list.get(position).getTime()));

        holder.deleteBtn.setVisibility(View.GONE);

        //处理评论列表
        setCommentListView(holder.commentListView,DataUtils.getCommentlist(),position);

        //处理点赞列表
        setPraiseListView(holder.praiseListView,DataUtils.getList(),position);
    }

    public int getItemCount() {
        return list.size();
    }

    /*
    处理点赞列表
     */
    private void setPraiseListView(PraiseListView praiseListView,final List<UserBean> likeList,final int position){
        if(this.list.get(position).getLikeNum()>0){
            praiseListView.setList(likeList);
            praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    //点击用户名
                    String userName=DataUtils.getList().get(position).getUsername();
                    Toast.makeText(MyApplication.getContext(), userName , Toast.LENGTH_SHORT).show();
                }
            });
            praiseListView.setVisibility(View.VISIBLE);
        }else {
            praiseListView.setVisibility(View.GONE);
        }
    }

    /*
    处理评论列表
     */

    private void setCommentListView(CommentListView commentListView, final List<CommentBean> commemtList, final int position){
        if(this.list.get(position).getCommentNum()>0){
            commentListView.setList(commemtList);
            commentListView.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //点击评论
                    String commemt=commemtList.get(position).getComment();
                    Toast.makeText(MyApplication.getContext(), commemt , Toast.LENGTH_SHORT).show();
                }
            });
            commentListView.setVisibility(View.VISIBLE);
        }else {
            commentListView.setVisibility(View.GONE);
        }
    }
}
