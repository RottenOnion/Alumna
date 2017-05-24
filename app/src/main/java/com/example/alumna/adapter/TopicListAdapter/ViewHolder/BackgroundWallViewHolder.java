package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumna.R;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;

/**
 * Created by Leebobo on 2017/5/10.
 */

public class BackgroundWallViewHolder extends RecyclerView.ViewHolder {

    public static final int BG_PICKER = 0x01;
    private View view;
    public ImageView backgroundIv;
    public ImageView headTv;
    public TextView nameTv;

    public BackgroundWallViewHolder(View itemView, final Context context) {
        super(itemView);
        view = itemView;
        backgroundIv = (ImageView) view.findViewById(R.id.backgroundIv);
        headTv = (ImageView) view.findViewById(R.id.headIv);
        nameTv = (TextView) view.findViewById(R.id.nameTv);

        final AlertDialog dialog = new AlertDialog
                .Builder(context)
                .setItems(new String[]{"更换相册封面"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            ImagePicker imagePicker = ImagePicker.getInstance();
                            imagePicker.setSelectLimit(1);    //选中数量限制
                            Activity curActivity=(Activity)view.getContext();
                            Intent intent = new Intent(curActivity, ImageGridActivity.class);
                            curActivity.startActivityForResult(intent, BG_PICKER);
                            Toast.makeText(curActivity,"成功",Toast.LENGTH_SHORT).show();
                     }
                    }
                })
                .create();

        backgroundIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                dialog.getWindow().setLayout(800,280);
            }
        });
    }
    public interface changeBackground{
          void uploadBg();
    }
}
