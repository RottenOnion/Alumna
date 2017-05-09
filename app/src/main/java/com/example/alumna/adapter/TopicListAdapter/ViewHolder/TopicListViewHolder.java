package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.adapter.TopicListAdapter.TopicListAdapter;
import com.example.alumna.bean.CommentBean;
import com.example.alumna.bean.TopicBean;
import com.example.alumna.bean.UserBean;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.example.alumna.utils.Image.ImageUtil;
import com.example.alumna.widgets.CommentListView;
import com.example.alumna.widgets.PraiseListView;
import com.example.alumna.widgets.SnsPopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.alumna.utils.ParseUtil.StringParseTime;

/**
 * Created by Leebobo on 2017/5/3.
 */

public abstract class TopicListViewHolder extends RecyclerView.ViewHolder {

    public final static int TYPE_TEXT = 1;
    public final static int TYPE_IMAGE = 2;

    public ViewHolderPresenter presenter;
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

    public TopicListViewHolder(View itemView,int type) {
        super(itemView);
        this.viewType=type;
        presenter=new ViewHolderPresenter(this);

        ViewStub viewStub = (ViewStub) itemView.findViewById(R.id.viewStub);

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
        line.setVisibility(View.GONE);
    }

    public abstract void initSubView(int viewType, ViewStub viewStub);

    public void loadhead(String url){
        ImageUtil.LoadImageFromUrl(this.head,url);
    }

    public void setData(TopicBean topic){
        viewType=Integer.valueOf(topic.getType());
        imfor.setText(topic.getImfor());
        name.setText(topic.getUsername());
        location.setText(topic.getLocation());
        time.setText(StringParseTime(topic.getTime()));

        loadhead(topic.getHead());

        deleteBtn.setVisibility(View.GONE);

        if(topic.getCommentNum()>0||topic.getLikeNum()>0){
            line.setVisibility(View.VISIBLE);
            if(topic.getLikeNum()>0){
                presenter.loadLikeList(topic.getTid());
            }else {
                praiseListView.setVisibility(View.GONE);
            }
            if(topic.getCommentNum()>0){
                presenter.loadCommentList(topic.getTid());
            }else {
                commentListView.setVisibility(View.GONE);
            }
        }
    }

    public void initPopupWindow(int tid) {
        snsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showPopupWindow(view);
            }
        });
        popupWindow.setItemClickListener(new PopupItemClickListener(tid));

    }

    public void setPraiseListView(ArrayList<UserBean> list){
        praiseListView.setList(list);
    }

    public void setCommentListView(ArrayList<CommentBean>list){
        commentListView.setList(list);
    }

    private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{
        //动态在列表中的位置
        private long lasttime = 0;
        private int tid;
        //private int position;

        public PopupItemClickListener(final int tid){
            //this.position = position;
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
                    presenter.setComment();
                    break;
                default:
                    break;
            }
        }
    }
}
