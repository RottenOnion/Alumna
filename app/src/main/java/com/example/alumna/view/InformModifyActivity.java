package com.example.alumna.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.InformModifyPresenter;
import com.example.alumna.view.Interface.InformModifyViewImpl;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class InformModifyActivity extends AppCompatActivity implements View.OnClickListener, InformModifyViewImpl {

    private InformModifyPresenter presenter;
    private CircleImageView head_view;
    private TextView gender_text,grade_text,name_text,school_text,location_text,signature_text,wechat_text;
    private static final int HEAD_PICKER=0x01;

    private Toolbar toolbar;
    private Button backBtn;
    private AlertDialog genderDialog,gradeDialog,nameDialog,schoolDialog,locationDialog,signatureDialog,wechatDialog;
    private ProgressDialog mLoadingDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_inform);

        //findview
        head_view=(CircleImageView)findViewById(R.id.head_view);

        name_text=(TextView)findViewById(R.id.name_text) ;
        gender_text=(TextView)findViewById(R.id.gender_text);
        school_text=(TextView)findViewById(R.id.school_text);
        grade_text=(TextView)findViewById(R.id.grade_text);
        location_text=(TextView)findViewById(R.id.location_text);
        signature_text=(TextView)findViewById(R.id.signature_text);
        wechat_text = (TextView)findViewById(R.id.wechat_text);

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoadingDialog.setMessage("修改中...");

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        backBtn=(Button) findViewById(R.id.back_Btn) ;


        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //初始化一系列Dialog
        initDialog();

        //set listener
        head_view.setOnClickListener(this);
        presenter = new InformModifyPresenter(this);
        gender_text.setOnClickListener(this);
        grade_text.setOnClickListener(this);
        name_text.setOnClickListener(this);
        school_text.setOnClickListener(this);
        signature_text.setOnClickListener(this);
        location_text.setOnClickListener(this);
        wechat_text.setOnClickListener(this);



        //load
        presenter.loadImfor(MyApplication.getcurUser().getUid());







    }

    private void initDialog() {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //性别
        final ChoiceOnClickListener genderChoiceListener = new ChoiceOnClickListener();
        genderDialog = new AlertDialog.Builder(this).setTitle("性别")
                .setSingleChoiceItems(new String[]{"男", "女"}, 0, genderChoiceListener)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int w = genderChoiceListener.getWhich();
                        if (w == 0) {
                            gender_text.setText("男");
                            gender_text.setTag(R.id.gender_id,1);
                        } else if (w == 1) {
                            gender_text.setText("女");
                            gender_text.setTag(R.id.gender_id,2);
                        }
                    }

                }).setNegativeButton("取消",null)
                .create();

        //年级
        final ChoiceOnClickListener gradeChoiceListener = new ChoiceOnClickListener();
        final String gradeStr[] = new String[]{"大一", "大二","大三","大四","研一","研二","研三"};
        gradeDialog = new AlertDialog.Builder(this)
                .setTitle("性别")
                .setSingleChoiceItems(gradeStr, 0, gradeChoiceListener)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int w = gradeChoiceListener.getWhich();
                        Log.d("ccl","" + w);
                        Log.d("ccl","" + gradeStr[w]);
                        grade_text.setText(gradeStr[w]);
                    }

                })
                .setNegativeButton("取消",null)
                .create();

        //姓名
        LinearLayout nameLayout = (LinearLayout)inflater.inflate(R.layout.dialog_view, null);
        nameDialog = createEditText(nameLayout,"姓名","请输入姓名",name_text);

        //学校
        LinearLayout schoolLayout = (LinearLayout)inflater.inflate(R.layout.dialog_view, null);
        schoolDialog = createEditText(schoolLayout,"学校","请输入学校名称",school_text);

        //地点
        LinearLayout locationLayout = (LinearLayout)inflater.inflate(R.layout.dialog_view, null);
        locationDialog = createEditText(locationLayout,"地点","请输入自己常出没的地点",location_text);

        //签名
        LinearLayout signatureLayout = (LinearLayout)inflater.inflate(R.layout.dialog_view, null);
        signatureDialog = createEditText(signatureLayout,"签名","别懒，快写签名",signature_text);

        //微信
        LinearLayout wechatLayout = (LinearLayout)inflater.inflate(R.layout.dialog_view, null);
        wechatDialog = createEditText(wechatLayout,"微信号","请输入微信号",wechat_text);
        wechatDialog.setIcon(R.drawable.icon_wechat);


    }

    private AlertDialog createEditText(LinearLayout layout, String tittle, String hint, final TextView view) {
        final EditText editext = (EditText) layout.findViewById(R.id.edit_text);
        editext.setHint(hint);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(tittle)
                .setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editext.getText().toString();
                        view.setText(name);
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        return dialog;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.UpdateImfor(MyApplication.getcurUser().getUid());
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(1);    //选中数量限制
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_view:
                initImagePicker();
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, HEAD_PICKER);
                break;

            case R.id.gender_text:
                genderDialog.show();
                break;

            case R.id.grade_text:
                gradeDialog.show();
                break;

            case R.id.name_text:
                nameDialog.show();
                break;

            case R.id.school_text:
                schoolDialog.show();
                break;

            case R.id.location_text:
                locationDialog.show();
                break;

            case R.id.signature_text:
                signatureDialog.show();
                break;

            case R.id.wechat_text:
                wechatDialog.show();
                break;

            default:break;
        }
    }

    /**
     * 监听系统按钮
     * @param keyCode   按钮（BACK,HOME）
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mLoadingDialog.show();
            presenter.UpdateImfor(MyApplication.getcurUser().getUid());
        }
        return false;
    }

    @Override
    public void showInform(UserBean user) {
        Glide.with(this).load(user.getHead()).error(R.drawable.ic_default_head).into(head_view);
        head_view.setTag(R.id.image_url,user.getHead());
        name_text.setText(user.getUsername());
        gender_text.setTag(R.id.gender_id,user.getSex());
        if(user.getSex()==1){
            gender_text.setText("男");
        }else if (user.getSex()==2){
            gender_text.setText("女");
        }
        school_text.setText(user.getSchool());
        grade_text.setText(user.getGrade());
        location_text.setText(user.getLocation());
        signature_text.setText(user.getSignature());
    }

    @Override
    public HashMap<String,Object> modifyInform() {
        HashMap<String,Object>params =new HashMap<>();
        params.put("head",head_view.getTag(R.id.image_url).toString());
        params.put("username",name_text.getText().toString());
        params.put("sex",gender_text.getTag(R.id.gender_id).toString());
        params.put("school",school_text.getText().toString());
        params.put("grade",grade_text.getText().toString());
        params.put("location",location_text.getText().toString());
        params.put("signature",signature_text.getText().toString());
        params.put("wechat",wechat_text.getText().toString());
        return params;
    }

    @Override
    public void showImageToast() {
        Toast.makeText(this,"头像上传成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInformToast() {
        Toast.makeText(this,"个人信息修改成功",Toast.LENGTH_LONG).show();
        mLoadingDialog.dismiss();
        finish();
    }

    @Override
    public void ImageUploadSuccess(String url) {
        head_view.setTag(R.id.image_url,url);
        mLoadingDialog.dismiss();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == HEAD_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Glide.with(this).
                        load(images.get(0).path).into(head_view);
                presenter.UploadImage(images);
                mLoadingDialog.show();
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ChoiceOnClickListener implements DialogInterface.OnClickListener {

        private int which = 0;
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            this.which = which;
        }

        public int getWhich() {
            return which;
        }
    }
}
