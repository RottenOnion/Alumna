package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Http.HttpRequestCallback;
import com.example.alumna.utils.Http.HttpUtil;
import com.example.alumna.utils.Image.ImageUtil;
import com.example.alumna.widgets.CommentListView;
import com.example.alumna.widgets.PraiseListView;
import com.example.alumna.widgets.SnsPopupWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leebobo on 2017/5/3.
 */

public abstract class TopicListViewHolder extends RecyclerView.ViewHolder {

    public final static int TYPE_TEXT = 1;
    public final static int TYPE_IMAGE = 2;

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

}
