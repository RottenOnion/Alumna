package com.example.alumna.adapter.TopicListAdapter;

import android.view.View;
import android.view.ViewStub;

/**
 * Created by Leebobo on 2017/5/3.
 */

public class TextViewHolder extends TopicListViewHolder {
    public TextViewHolder(View itemView) {
        super(itemView, TYPE_TEXT);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {

    }
}
