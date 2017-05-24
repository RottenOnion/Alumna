package com.example.alumna.adapter.TopicListAdapter.ViewHolder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumna.R;

/**
 * Created by Leebobo on 2017/5/10.
 */

public class BackgroundWallViewHolder extends RecyclerView.ViewHolder {

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
                            /**
                             请在此处填写跳转到选择相片逻辑
                             */
                            Toast.makeText(context, "选择相片", Toast.LENGTH_SHORT).show();
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



}
