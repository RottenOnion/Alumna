package com.example.alumna.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.UpdateImforPresenter;
import com.example.alumna.utils.DataUtils;
import com.example.alumna.utils.Image.GlideImageLoader;
import com.example.alumna.utils.Image.ImageUtil;
import com.example.alumna.view.Interface.UpdateImforViewImpl;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UpdateImforActivity extends AppCompatActivity implements View.OnClickListener, UpdateImforViewImpl {

    private UpdateImforPresenter presenter;
    private CircleImageView head_view;
    private EditText name_text,school_text,grade_text,location_text,signature_text;
    private static final int HEAD_PICKER=0x01;

    private Toolbar toolbar;
    private Button backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_infor);

        //findview
        head_view=(CircleImageView)findViewById(R.id.head_view);
        school_text=(EditText)findViewById(R.id.school_text);
        grade_text=(EditText)findViewById(R.id.grade_text);
        //home_text=(EditText)findViewById(R.id.home_text);
        location_text=(EditText)findViewById(R.id.location_text);
        signature_text=(EditText)findViewById(R.id.signature_text);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        backBtn=(Button) findViewById(R.id.back_Btn) ;
        setSupportActionBar(toolbar);

        //set listener
        head_view.setOnClickListener(this);
        location_text.setOnClickListener(this);

        presenter = new UpdateImforPresenter(this);


        //load
        presenter.loadImfor(DataUtils.curUser.getUid());

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_view:
                initImagePicker();
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, HEAD_PICKER);
                startActivity(intent);
                break;
            case R.id.location_text:
                location_text.setFocusable(true);
            default:break;
        }
    }

    @Override
    public void showImfor(UserBean user) {
        Glide.with(this).load(user.getHead()).into(head_view);
        name_text.setText(user.getUsername());
        //....
    }

    @Override
    public void saveImfor() {
        //gettext from editview
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == HEAD_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Glide.with(MyApplication.getContext()).
                        load(images.get(0).path).into(head_view);
                presenter.UploadImage(images);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
