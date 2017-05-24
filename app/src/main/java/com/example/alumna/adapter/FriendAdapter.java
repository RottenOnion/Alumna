package com.example.alumna.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alumna.R;
import com.example.alumna.bean.UserBean;

import java.util.List;

/**
 * Created by py on 2017/5/24.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    private List<UserBean> mDatas;
    private Context mContext;

    public FriendAdapter(List<UserBean> mDatas,Context context) {
        this.mDatas = mDatas;
        mContext = context;
    }

    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,parent,false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {
        UserBean user = mDatas.get(position);

        Glide.with(mContext)
                .load(user.getHead())
                .into(holder.headView);

        holder.textName.setText(user.getUsername());
        holder.textTime.setText(user.getSchool());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class FriendHolder extends RecyclerView.ViewHolder {

        private ImageView headView;
        private TextView textName;
        private TextView textTime;

        public FriendHolder(View itemView) {
            super(itemView);
            headView = (ImageView) itemView.findViewById(R.id.head_view);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textTime = (TextView) itemView.findViewById(R.id.message_text);
        }
    }
}
