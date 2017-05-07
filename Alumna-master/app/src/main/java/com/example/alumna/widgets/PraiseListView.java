package com.example.alumna.widgets;


import android.content.Context;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.alumna.bean.UserBean;

import java.util.List;

/**
 * Created by Leebobo on 2017/4/26.
 */

public class PraiseListView extends TextView {

    private List<UserBean> list;
    private OnItemClickListener onItemClickListener;

   // private OnItemClickListener onItemClickListener;

    public PraiseListView(Context context) {
        super(context);
    }

    public PraiseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PraiseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(List<UserBean> list){
        this.list=list;
        showList();
    }

    void showList(){

        SpannableStringBuilder builder = new SpannableStringBuilder();
        //点赞人数为0时候不显示
        if(list!=null&&list.size()>0){
            builder.append("❤");
            UserBean tmp=null;
            for (int i=0;i<list.size();++i){
                tmp=list.get(i);
                if(tmp!=null){
                    builder.append(setClickableSpan(tmp.getUsername(),i));
                }
                if(i!=list.size()-1){
                    builder.append(",");
                }
            }
        }
        setText(builder);
    }

     private SpannableString setClickableSpan(String textStr, final int position) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new ClickableSpan(){
                                    @Override
                                    public void onClick(View widget) {

                                        if(onItemClickListener!=null){
                                            onItemClickListener.onClick(position);

                                        }
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    public static interface OnItemClickListener{
        public void onClick(int position);
    }
}
