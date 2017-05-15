package com.example.alumna.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.bean.UserBean;

import java.util.List;

/**
 * Created by py on 2017/5/15.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyHolder> {

    private List<UserBean> mDatas;

    public NearbyAdapter(List<UserBean> mDatas) {
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

        /**
         * 测试版代码，同意绑定数据
         */
        holder.headView.setImageResource(R.drawable.test_touxiang);
        holder.textName.setText("张敬轩");
        holder.btnGender.setBackgroundResource(R.drawable.button_male_shape);
        holder.btnGender.setText("男");
        holder.btnGrade.setBackgroundResource(R.drawable.button_grade_shape);
        holder.btnGrade.setText("大三");
        holder.textSchool.setText("华南理工大学");


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
