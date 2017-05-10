package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Image.ImageUtil;
import com.example.alumna.widgets.CommentListView;
import com.example.alumna.widgets.EditTextPopupWindow;
import com.example.alumna.widgets.PraiseListView;
import com.example.alumna.widgets.SnsPopupWindow;

import java.util.ArrayList;


import static com.example.alumna.utils.ParseUtil.StringParseTime;

/**
 * Created by Leebobo on 2017/5/3.
 */

public abstract class TopicListViewHolder extends RecyclerView.ViewHolder {

    public final static int TYPE_TEXT = 1;
    public final static int TYPE_IMAGE = 2;

    public ViewHolderPresenter presenter;

    public View view;
    public int viewType;
    public ImageView head;
    public TextView name,time,location,imfor;

    public TextView deleteBtn;
    public ImageView snsBtn;

    //点赞列表
    public PraiseListView praiseListView;
    public View line;
    //评论列表
    public CommentListView commentListView;

    //弹出窗口
    public SnsPopupWindow popupWindow;

    //评论窗口
    public EditTextPopupWindow editTextPopupWindow;

    public TopicListViewHolder(View itemView,int type) {
        super(itemView);
        view=itemView;
        this.viewType=type;
        presenter=new ViewHolderPresenter(this);

        ViewStub viewStub = (ViewStub) itemView.findViewById(R.id.viewStub);
        initSubView(viewType, viewStub);
        head=(ImageView)itemView.findViewById(R.id.headIv);
        name=(TextView)itemView.findViewById(R.id.nameTv);
        time=(TextView)itemView.findViewById(R.id.timeTv);
        location=(TextView)itemView.findViewById(R.id.locationTv);
        imfor=(TextView)itemView.findViewById(R.id.imforTv);
        deleteBtn=(TextView)itemView.findViewById(R.id.deleteBtn);

        snsBtn=(ImageView)itemView.findViewById(R.id.snsBtn);

        praiseListView=(PraiseListView)itemView.findViewById(R.id.praiseList);
        line=(View)itemView.findViewById(R.id.line);
        commentListView=(CommentListView)itemView.findViewById(R.id.commentList);

        popupWindow=new SnsPopupWindow(itemView.getContext());
        editTextPopupWindow=new EditTextPopupWindow(itemView.getContext());

        line.setVisibility(View.GONE);
    }

    public abstract void initSubView(int viewType, ViewStub viewStub);

    public void setData(TopicBean topic){
        viewType=Integer.valueOf(topic.getType());
        imfor.setText(topic.getImfor());
        name.setText(topic.getUsername());
        location.setText(topic.getLocation());
        time.setText(StringParseTime(topic.getTime()));

        //加载头像
        ImageUtil.LoadImageFromUrl(this.head,topic.getHead());

        //删除按钮
        if(topic.getUid()==DataUtils.curUser.getUid()){
            deleteBtn.setVisibility(View.GONE);
        }

        if(topic.getCommentNum()>0||topic.getLikeNum()>0){
            line.setVisibility(View.VISIBLE);
            //处理点赞列表
            if(topic.getLikeNum()>0){
                presenter.loadLikeList(topic.getTid());
            }else {
                praiseListView.setVisibility(View.GONE);
            }

            //处理评论列表
            if(topic.getCommentNum()>0){
                presenter.loadCommentList(topic.getTid());
            }else {
                commentListView.setVisibility(View.GONE);
            }
        }

    }

    public void initPopupWindow(final int tid) {
        snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showPopupWindow(view);
            }
        });
        popupWindow.setItemClickListener(new PopupItemClickListener(tid));


    }

    public void setPraiseListView(final ArrayList<UserBean> list){
        praiseListView.notifyDataSetChanged(list);
        praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                presenter.getUserImfor(list.get(position).getUid());
            }
        });
    }

    public void setCommentListView(final ArrayList<CommentBean>list){
        commentListView.notifyDataSetChanged(list);
        commentListView.setOnItemClickListener(new CommentListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //评论
            }
        });
    }

    private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{

        private long lasttime = 0;
        private int tid;

        public PopupItemClickListener(final int tid){
            this.tid=tid;
        }

        @Override
        public void onItemClick(SnsPopupWindow.ActionItem actionitem, int position) {
            switch (position) {
                case 0://点赞、取消点赞
                    if(System.currentTimeMillis()- lasttime <500)//防止快速点击操作
                        return;
                    lasttime = System.currentTimeMillis();
                    presenter.setLike(DataUtils.curUser.getUid(),tid);
                    break;
                case 1://发布评论
                    editTextPopupWindow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()){
                                case R.id.commentEt:
                                    break;
                                case R.id.sendBtn:
                                    String comment=editTextPopupWindow.getComment();
                                    if (comment!=null) presenter.setComment(DataUtils.curUser.getUid(), tid,comment);
                                    break;
                                default:break;
                            }
                        }
                    });
                    editTextPopupWindow.showPopupWindow(view);

                    break;
                default:
                    break;
            }
        }
    }
}
