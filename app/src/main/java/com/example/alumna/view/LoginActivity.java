package com.example.alumna.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.presenter.LoginPresenter;
import com.example.alumna.view.Interface.LoginViewImpl;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements LoginViewImpl ,OnClickListener{


    private AutoCompleteTextView phoneTv;
    private EditText passwordEv;
    private View LoginView;
    private Button signBtn;
    private TextView forgetpassword;
    private Button registerBtn;
    private ProgressDialog mLoadingDialog;

    //presenter
    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter=new LoginPresenter(this);

        LoginView=(LinearLayout)findViewById(R.id.loginView);
        phoneTv = (AutoCompleteTextView) findViewById(R.id.phoneTv);
        passwordEv = (EditText) findViewById(R.id.passwordEt);
        signBtn = (Button) findViewById(R.id.loginBtn);

        forgetpassword=(TextView)findViewById(R.id.foggetpassword) ;
        registerBtn=(Button) findViewById(R.id.registerBtn);

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoadingDialog.setMessage("正在登陆...");
        mLoadingDialog.setCancelable(false);

        forgetpassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        signBtn.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public Map<String, Object> getLoginImfor() {
        String phone = phoneTv.getText().toString();
        String password = passwordEv.getText().toString();
        HashMap<String, Object> params = new HashMap<>();
        params.put("phone",phone);
        params.put("password",password);

        return params;
    }

    @Override
    public void showProgressBar() {
        mLoadingDialog.show();
        LoginView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mLoadingDialog.dismiss();
        LoginView.setVisibility(View.VISIBLE);
    }

    @Override
    public void StartMainActivity() {
        Intent i=new Intent();
        i.setClass(this,MainActivity.class);
        startActivity(i);
        LoginActivity.this.finish();
    }

    public void StartRegisterActivity(){
        Intent i=new Intent();
        i.setClass(this,RegisterActivity.class);
        startActivity(i);
        LoginActivity.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn:
                StartRegisterActivity();
                break;
            case R.id.foggetpassword:
                Toast.makeText(MyApplication.getContext(),"忘记密码",Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginBtn:
                presenter.Login();
            default:break;
        }
    }
}

