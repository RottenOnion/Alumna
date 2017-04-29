package com.example.alumna.widgets;


import android.content.Context;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import android.text.TextPaint;
import android.text.method.BaseMovementMethod;
import android.text.method.Touch;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.alumna.MyApplication;
import com.example.alumna.R;
import com.example.alumna.bean.UserBean;

import java.util.List;

/**
 * Created by Leebobo on 2017/4/26.
 * 点赞列表
 */

public class PraiseListView extends TextView {

    private List<UserBean> list;
    private OnItemClickListener onItemClickListener;

    private final static int FONT_FRONT_COLOR = R.color.praise_listview_font_front_color;

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
            builder.append(" ❤ ");
            UserBean tmp=null;
            for (int i=0;i<list.size();++i){
                tmp=list.get(i);
                if(tmp!=null){
                    builder.append(setSpanStyle(tmp.getUsername(),i));
                }
                if(i!=list.size()-1){
                    builder.append(",");
                }
            }
        }
        setText(builder);
        //setMovementMethod(LinkMovementMethod.getInstance());
        setMovementMethod(new PraiseListViewMethod());

    }

     private SpannableString setSpanStyle(String textStr, final int position) {
         SpannableString SpanText = new SpannableString(textStr);

         //设置点击事件
         SpanText.setSpan(new ClickableSpan(){
                                    @Override
                                    public void onClick(View widget) {

                                        if(onItemClickListener!=null){
                                            onItemClickListener.onClick(position);
                                        }
                                    }
                                    public void updateDrawState(TextPaint ds) {
                                        ds.setUnderlineText(false);
                                    }

                          }, 0, SpanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

         //设置前景色
         SpanText.setSpan(new ForegroundColorSpan(MyApplication.getContext().getResources().getColor(FONT_FRONT_COLOR)),
                 0,SpanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


         return SpanText;
    }

    public static interface OnItemClickListener{
        public void onClick(int position);
    }
}


//设置点击spanString时候文字背景的变化
class PraiseListViewMethod extends BaseMovementMethod {

    private final static int DEFAULT_CLICKABLE_COLOR_ID = R.color.default_clickable_color;
    /**整个textView的背景色*/
    private int textViewBgColor;
    /**点击部分文字时部分文字的背景色*/
    private int clickableSpanBgColor;

    private BackgroundColorSpan mBgSpan;
    private ClickableSpan[] mClickLinks;
    private boolean isPassToTv = false;
    /**
     * true：响应textview的点击事件， false：响应设置的clickableSpan事件
     */
    public boolean isPassToTv() {
        return isPassToTv;
    }
    private void setPassToTv(boolean isPassToTv){
        this.isPassToTv = isPassToTv;
    }

    public PraiseListViewMethod(){
        this.clickableSpanBgColor = MyApplication.getContext().getResources().getColor(DEFAULT_CLICKABLE_COLOR_ID);
    }


    public boolean onTouchEvent(TextView widget, Spannable buffer,
                                MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            mClickLinks = buffer.getSpans(off, off, ClickableSpan.class);
            if(mClickLinks.length > 0){
                // 点击的是Span区域，不要把点击事件传递
                setPassToTv(false);
                Selection.setSelection(buffer,
                        buffer.getSpanStart(mClickLinks[0]),
                        buffer.getSpanEnd(mClickLinks[0]));
                //设置点击区域的背景色
                mBgSpan = new BackgroundColorSpan(clickableSpanBgColor);
                buffer.setSpan(mBgSpan,
                        buffer.getSpanStart(mClickLinks[0]),
                        buffer.getSpanEnd(mClickLinks[0]),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else{
                setPassToTv(true);
                // textview选中效果
                widget.setBackgroundColor(textViewBgColor);
            }

        }else if(action == MotionEvent.ACTION_UP){
            if(mClickLinks.length > 0){
                mClickLinks[0].onClick(widget);
                if(mBgSpan != null){//移除点击时设置的背景span
                    buffer.removeSpan(mBgSpan);
                }
            }else{

            }
            Selection.removeSelection(buffer);
            widget.setBackgroundResource(R.color.transparent);
        }else if(action == MotionEvent.ACTION_MOVE){
            //这种情况不用做处理
        }else{
            if(mBgSpan != null){//移除点击时设置的背景span
                buffer.removeSpan(mBgSpan);
            }
            widget.setBackgroundResource(R.color.transparent);
        }
        return Touch.onTouchEvent(widget, buffer, event);
    }
}