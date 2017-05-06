package com.example.alumna.view.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.widgets.CommentListView;
import com.example.alumna.widgets.PraiseListView;

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

    public PraiseListView praiseListView;
    public CommentListView commentListView;

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
        commentListView=(CommentListView)itemView.findViewById(R.id.commentList);
    }

    public abstract void initSubView(int viewType, ViewStub viewStub);

}
