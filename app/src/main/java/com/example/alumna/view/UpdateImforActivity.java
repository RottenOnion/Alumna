package com.example.alumna.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumna.R;
import com.example.alumna.bean.UserBean;
import com.example.alumna.presenter.UpdateImforPresenter;
import com.example.alumna.view.Interface.UpdateImforViewImpl;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UpdateImforActivity extends AppCompatActivity implements View.OnClickListener, UpdateImforViewImpl {

    private UpdateImforPresenter presenter;
    private CircleImageView head_view;
    private TextView name_text,school_text,grade_text,home_text,location_text,signature_text;

    private Toolbar toolbar;
    private Button backBtn;
    public UpdateImforActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_infor);

        //findview
        head_view=(CircleImageView)findViewById(R.id.head_view);
        school_text=(TextView)findViewById(R.id.school_text);
        grade_text=(TextView)findViewById(R.id.grade_text);
        home_text=(TextView)findViewById(R.id.home_text);
        location_text=(TextView)findViewById(R.id.location_text);
        signature_text=(TextView)findViewById(R.id.signature_text);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        backBtn=(Button) findViewById(R.id.back_Btn) ;
        setSupportActionBar(toolbar);

        //set listener


        presenter = new UpdateImforPresenter(this);


        //load
        presenter.loadImfor(1);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showImfor(UserBean user) {

    }

    @Override
    public void saveImfor() {
        //gettext from editview
    }
}
