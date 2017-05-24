package com.example.alumna.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alumna.R;
import com.example.alumna.bean.UserBean;

import java.util.List;

/**
 * Created by py on 2017/5/15.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyHolder> {

    private List<UserBean> mDatas;
    private Context mContext;


    public NearbyAdapter(List<UserBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    public void setDatas(List<UserBean> mDatas) {
        this.mDatas = mDatas;

    }


    @Override
    public NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_item,parent,false);
        return new NearbyHolder(view);
    }

    @Override
    public void onBindViewHolder(NearbyHolder holder, int position) {
        UserBean user = mDatas.get(position);

        Glide.with(mContext).load(user.getHead())
                .into(holder.headView);
        holder.headView.setImageResource(R.drawable.test_touxiang);
        holder.textName.setText(user.getUsername());
        if (user.getSex() == 1) {
            holder.btnGender.setBackgroundResource(R.drawable.button_male_shape);
            holder.btnGender.setText("男");
        } else if (user.getSex() == 2) {
            holder.btnGender.setBackgroundResource(R.drawable.button_female_shape);
            holder.btnGender.setText("女");
        }
        holder.btnGrade.setBackgroundResource(R.drawable.button_grade_shape);
        holder.btnGrade.setText(user.getGrade());
        holder.textSchool.setText(user.getSchool());


    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class NearbyHolder extends RecyclerView.ViewHolder {

        private ImageView headView;
        private TextView textName;
        private Button btnGender;
        private Button btnGrade;
        private TextView textSchool;

        public NearbyHolder(View itemView) {
            super(itemView);
            headView = (ImageView) itemView.findViewById(R.id.head_view);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            btnGender = (Button) itemView.findViewById(R.id.btn_gender);
            btnGrade = (Button) itemView.findViewById(R.id.btn_grade);
            textSchool = (TextView) itemView.findViewById(R.id.text_school);
        }
    }
}
