package com.example.alumna.bean;

/**
 * Created by Leebobo on 2017/5/5.
 */

public class JsonBean<T> {
    private String status;
    private T data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
