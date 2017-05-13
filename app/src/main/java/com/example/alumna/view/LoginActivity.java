package com.example.alumna.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    private ProgressBar progressView;
    private View LoginView;
    private Button signBtn;
    private TextView forgetpassword;
    private TextView register;

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
        signBtn = (Button) findViewById(R.id.signBtn);
        progressView = (ProgressBar) findViewById(R.id.login_progress);
        forgetpassword=(TextView)findViewById(R.id.foggetpassword) ;
        register=(TextView)findViewById(R.id.register);

        signBtn.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
        register.setOnClickListener(this);
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
        progressView.setVisibility(View.VISIBLE);
        LoginView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressView.setVisibility(View.GONE);
        LoginView.setVisibility(View.VISIBLE);
    }

    @Override
    public void StartMainActivity() {
        Intent i=new Intent();
        i.setClass(this,MainActivity.class);
        startActivity(i);
        LoginActivity.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Toast.makeText(MyApplication.getContext(),"注册",Toast.LENGTH_SHORT).show();
                break;
            case R.id.foggetpassword:
                Toast.makeText(MyApplication.getContext(),"忘记密码",Toast.LENGTH_SHORT).show();
                break;
            case R.id.signBtn:
                presenter.Login();
            default:break;
        }
    }
}

