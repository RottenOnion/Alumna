package com.example.alumna.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alumna.R;
import com.example.alumna.presenter.SelectPhotoPresenter;
import com.example.alumna.utils.Image.GlideImageLoader;
import com.example.alumna.view.Interface.SelectPhotoViewImpl;
import com.example.alumna.widgets.ImageListView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25.
 */

public class SelectPhotoActivity extends AppCompatActivity implements View.OnClickListener, SelectPhotoViewImpl {

    ImageListView imageListView;

    //private Button select;
    private SelectPhotoPresenter presenter;
    private static final int IMAGE_PICKER=0x01;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_list_view);
        imageListView=(ImageListView)findViewById(R.id.img_list);

        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imageListView.notifyDataSetChanged(images);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void showImage() {

    }
}
