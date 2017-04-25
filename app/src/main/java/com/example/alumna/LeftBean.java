package com.example.alumna;

/**
 * Created by py on 2017/4/24.
 */

public class LeftBean {
    private int imageId;
    private String tittleText;


    public int getImageId() {
        return imageId;
    }

    public LeftBean(int imageId, String tittleText) {
        this.imageId = imageId;
        this.tittleText = tittleText;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTittleText() {
        return tittleText;
    }

    public void setTittleText(String tittleText) {
        this.tittleText = tittleText;
    }
}
