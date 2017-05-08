package com.example.alumna.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.JsonBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.widgets.CommentListView;
import com.example.alumna.widgets.PraiseListView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.Call;

import static com.example.alumna.utils.ParseUtil.StringParseTime;



public class TopicListAdapter extends BaseRecycleViewAdapter{

    private ArrayList<TopicBean> list ;
    private Context context;
    private LayoutInflater inflater;

    public TopicListAdapter(Context context,ArrayList<TopicBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_topic_item,parent, false);
        if (viewType==TopicListViewHolder.TYPE_TEXT) {
            holder= new TextViewHolder(view);
        }else if (viewType==TopicListViewHolder.TYPE_IMAGE){
           holder=new ImageViewHolder(view);
        }
        //holder= new TextViewHolder(view);
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

        if(list.get(position).getCommentNum()==0||list.get(position).getLikeNum()==0){
            holder.line.setVisibility(View.GONE);
        }
        //处理评论列表
        setCommentListView(holder,position);

        //处理点赞列表
        setPraiseListView(holder,position);
    }

    public int getItemCount() {
        return list.size();
    }

    /*
    处理点赞列表
     */
    private void setPraiseListView(final TopicListViewHolder holder,final int position){
        if(this.list.get(position).getLikeNum()>0){

            final int tid=list.get(position).getTid();
            holder.loadLikeList(tid, new HttpRequestCallback<String>() {
                @Override
                public void onStart() {
                    holder.praiseListView.setVisibility(View.GONE);
                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onResponse(String  result) {
                    JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                    JsonArray jsonArray=jsonObject.getAsJsonArray("List");
                    Gson gson=new Gson();
                    ArrayList<UserBean> likeList=new ArrayList<>();
                    for (JsonElement bean:jsonArray){
                        UserBean user=gson.fromJson(bean,new TypeToken<UserBean>(){ }.getType());
                        likeList.add(user);
                        System.out.println(this.getClass().getName()+"----"+user.getUsername());
                    }
                    holder.praiseListView.setList(likeList);
                    holder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            //点击用户名
                        }
                    });
                    holder.praiseListView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call call) {

                }
            });
        }else {
            holder.praiseListView.setVisibility(View.GONE);
        }
    }

    /*
    处理评论列表
     */

    private void setCommentListView(final TopicListViewHolder holder,  final int position){
        if(this.list.get(position).getCommentNum()>0){

            final int tid=list.get(position).getTid();
            holder.loadCommentList(tid, new HttpRequestCallback<String>() {
                @Override
                public void onStart() {
                    holder.commentListView.setVisibility(View.GONE);
                }

                @Override
                public void onFinish() {
                    //holder.commentListView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onResponse(String result) {
                    JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();

                    JsonArray jsonArray=jsonObject.getAsJsonArray("list");

                    Gson gson=new Gson();
                    ArrayList<CommentBean>commentlist=new ArrayList<>();
                    for (JsonElement bean:jsonArray){
                        CommentBean comment=gson.fromJson(bean,new TypeToken<CommentBean>(){ }.getType());
                        commentlist.add(comment);
                        System.out.println(this.getClass().getName()+"----"+comment.getComment());
                    }

                    holder.commentListView.setList(commentlist);
                    holder.commentListView.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            //点击评论
                            //String commemt=commentlist.get(position).getComment();
                            //Toast.makeText(MyApplication.getContext(), commemt , Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.commentListView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call call) {
                    holder.commentListView.setVisibility(View.GONE);
                }
            });

        }else {
            holder.commentListView.setVisibility(View.GONE);
        }
    }
}
