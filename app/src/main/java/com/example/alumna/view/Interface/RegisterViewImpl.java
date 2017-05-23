package com.example.alumna.view.Interface;

import java.util.HashMap;

/**
 * Created by Leebobo on 2017/5/23.
 */

public interface RegisterViewImpl {
    String getUsername();
    String getPhone();
    String getPassword();
    void showStatus(int status);
    void RegisterSuccess();
}
