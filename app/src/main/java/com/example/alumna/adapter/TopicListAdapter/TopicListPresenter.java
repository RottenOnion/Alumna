package com.example.alumna.adapter.TopicListAdapter;

import android.util.Log;
import android.view.View;

import com.example.alumna.adapter.TopicListAdapter.ViewHolder.TopicListViewHolder;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
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

/**
 * Created by Leebobo on 2017/5/9.
 */

public class TopicListPresenter implements TopicListPresenetImpl{
    private TopicListModel tModel;
    private TopicListAdapter tView;

    public TopicListPresenter(TopicListAdapter view){
        tView=view;
        tModel=new TopicListModel();
    }


    @Override
    public void loadLikeList(final TopicListViewHolder holder, final int position) {
        ArrayList<TopicBean> list=tView.getTopicList();
        final int tid=list.get(position).getTid();
        tModel.getLikeList(tid, new HttpRequestCallback<String>() {
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
                    final ArrayList<UserBean> likeList=new ArrayList<>();
                    for (JsonElement bean:jsonArray){
                        UserBean user=gson.fromJson(bean,new TypeToken<UserBean>(){ }.getType());
                        likeList.add(user);
                    }
                    holder.praiseListView.setList(likeList);

                    holder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            //点击用户名
                            getUserImfor(likeList.get(position).getUid());

                            //Log.i(this.getClass().getName(),list.get(position).getUsername());
                        }
                    });

                    holder.praiseListView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call call) {

                }
            });
    }

    @Override
    public void loadCommentList(final TopicListViewHolder holder, final int position) {
        final ArrayList<TopicBean> list=tView.getTopicList();

        final int tid=list.get(position).getTid();
        tModel.getCommentList(tid, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {
                    holder.commentListView.setVisibility(View.GONE);
                }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();

                JsonArray jsonArray=jsonObject.getAsJsonArray("list");

                Gson gson=new Gson();
                final ArrayList<CommentBean> commentlist=new ArrayList<>();
                for (JsonElement bean:jsonArray){
                    CommentBean comment=gson.fromJson(bean,new TypeToken<CommentBean>(){ }.getType());
                    commentlist.add(comment);
                }

                holder.commentListView.setList(commentlist);
                holder.commentListView.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //点击评论
                        //String commemt=commentlist.get(position).getComment();
                        getUserImfor(commentlist.get(position).getUser().getUid());
                    }
                });
                holder.commentListView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call call) {
                holder.commentListView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void setLike(final int uid,final int tid) {
        Log.i(this.getClass().getName(),uid+"    like    "+tid);
        tModel.setLike(uid, tid, new HttpRequestCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResponse(String result) {
                JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
                Log.i(this.getClass().getName(),jsonObject.get("status").getAsString());
                if (jsonObject.get("status").getAsString().equals("1")||jsonObject.get("status").getAsString().equals("0")){
                    //操作成功status=0 or 1
                }
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    @Override
    public void setComment() {
        Log.i(this.getClass().getName(),"comment");
    }

    @Override
    public void getUserImfor(int uid) {
        Log.i(this.getClass().getName(),""+uid);
    }
}
