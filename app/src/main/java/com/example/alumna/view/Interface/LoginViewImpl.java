package com.example.alumna.view.Interface;
import java.util.Map;

/**
 * Created by Leebobo on 2017/4/26.
 */

public interface LoginViewImpl {
    Map<String, Object> getLoginImfor() ;

    void showProgressBar();

    void hideProgressBar();

    void StartMainActivity();
}
