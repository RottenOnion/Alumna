package com.example.alumna.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alumna.bean.LeftBean;
import com.example.alumna.R;

import java.util.ArrayList;

/**
 * Created by py on 2017/4/24.
 */

public class LeftDrawerAdapter extends RecyclerView.Adapter<LeftDrawerAdapter.LeftViewHolder> {

    //private static View.OnClickListener mClickListener;
    private onItemClickListener listener;
    private LayoutInflater mInflater;
    private ArrayList<LeftBean> mDatas;

    public LeftDrawerAdapter(ArrayList<LeftBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_drawer_item,parent,false);
        return new LeftViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(LeftViewHolder holder, final int position) {
        LeftBean data = mDatas.get(position);
        holder.imageView.setImageResource(data.getImageId());
        holder.tittle.setText(data.getTittleText());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public static class LeftViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout view;
        private ImageView imageView;
        private TextView tittle;
        private onItemClickListener mlistener;

        public LeftViewHolder(View itemView,onItemClickListener listener) {
            super(itemView);
            view = (LinearLayout) itemView.findViewById(R.id.left_drawer_item);
            imageView = (ImageView) itemView.findViewById(R.id.left_drawer_image);
            tittle = (TextView) itemView.findViewById(R.id.left_drawer_tittle);
            this.mlistener=listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mlistener.onItemClick(v,getPosition());
        }
    }

    public interface onItemClickListener{
         void onItemClick(View v,int position);
    }

}
