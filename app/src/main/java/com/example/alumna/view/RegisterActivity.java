package com.example.alumna.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumna.R;
import com.example.alumna.presenter.RegisterPresenter;
import com.example.alumna.utils.StringUtil;
import com.example.alumna.view.Interface.RegisterViewImpl;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegisterViewImpl {

    private EditText phone_tv,name_tv,password1_et,password2_et;
    private Button register_btn;
    private TextView login_btn;
    private LinearLayout register_layout,welcome_layout;
    private Button jump_btn,modify_btn;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        presenter=new RegisterPresenter(this);
    }

    private void initView() {
        register_layout=(LinearLayout)findViewById(R.id.register_view);
        welcome_layout=(LinearLayout)findViewById(R.id.welcome_view) ;
        phone_tv=(EditText) findViewById(R.id.phoneTv);
        name_tv=(EditText)findViewById(R.id.usernameTv);
        password1_et=(EditText)findViewById(R.id.passwordEt1);
        password2_et=(EditText)findViewById(R.id.passwordEt2);
        register_btn=(Button)findViewById(R.id.register_btn);
        login_btn=(TextView)findViewById(R.id.login_tv);
        jump_btn=(Button)findViewById(R.id.jump_btn);
        modify_btn=(Button)findViewById(R.id.modify_inform_btn);

        phone_tv.setInputType(InputType.TYPE_CLASS_NUMBER);

        //listener
        register_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        jump_btn.setOnClickListener(this);
        modify_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                tryRegister();
                break;
            case R.id.login_tv:
                RegisterActivity.this.finish();
                break;
            case R.id.jump_btn:
                Intent i=new Intent();
                i.setClass(this,MainActivity.class);
                startActivity(i);
                RegisterActivity.this.finish();
                break;
            case R.id.modify_inform_btn:
                i=new Intent();
                i.setClass(this,InformModifyActivity.class);
                startActivity(i);
                RegisterActivity.this.finish();
                break;

        }
    }

    @Override
    public String getUsername() {
        return name_tv.getText().toString();
    }

    @Override
    public String getPhone() {
       return phone_tv.getText().toString();
    }

    @Override
    public String getPassword() {
        return password1_et.getText().toString();
    }

    @Override
    public void showStatus(int status) {
        register_btn.setClickable(true);

        switch (status){
            case 1:
                Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();break;
            case 0:
                Toast.makeText(this,"该手机号注册的用户已存在",Toast.LENGTH_SHORT).show();break;
            case -1:
                Toast.makeText(this,"其他错误",Toast.LENGTH_SHORT).show();break;
        }
    }

    @Override
    public void RegisterSuccess() {
        register_layout.setVisibility(View.GONE);
        welcome_layout.setVisibility(View.VISIBLE);
    }


    public void tryRegister(){
        //phone
        if(TextUtils.isEmpty(phone_tv.getText().toString())){
            Toast.makeText(this,"请输入注册用的手机号码",Toast.LENGTH_SHORT).show();
            phone_tv.requestFocus();
            return;
        }else if (!StringUtil.isMobile(phone_tv.getText().toString())){
            Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
            phone_tv.requestFocus();
            return;
        }
        //username
        if(TextUtils.isEmpty(name_tv.getText().toString())){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            name_tv.requestFocus();
            return;
        }

        //password
        if (TextUtils.isEmpty(password1_et.getText().toString())){
            Toast.makeText(getBaseContext(),"请输入密码！",Toast.LENGTH_SHORT).show();
            password1_et.requestFocus();
            return;
        }else if (!password1_et.getText().toString().equals(password2_et.getText().toString())){
            Toast.makeText(getBaseContext(),"两次密码不一致！",Toast.LENGTH_SHORT).show();
            password2_et.requestFocus();
            return;
        }
        register_btn.setClickable(false);
        presenter.register();
    }
}
