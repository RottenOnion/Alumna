package com.example.alumna.utils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leebobo on 2017/5/6.
 * 字符串格式转换工具类
 */

public class  ParseUtil {

    /**
     * 把key-value的map转化成String格式的工具类
     */
    public static String MapParseString(final Map<String, Object> params) {

        StringBuffer builder = new StringBuffer();
        if(null!=params &&!params.isEmpty())
    {
        builder.append("{");
        for (HashMap.Entry<String, Object> entry : params.entrySet()) {
            builder.append("\"").append(entry.getKey()).append("\"").
                    append(":");
            if (entry.getValue() instanceof Integer) {
                builder.append(entry.getValue()).append(",");
            } else {
                builder.append("\"").append(URLEncoder.encode(entry.getValue().toString())).append("\"").
                        append(",");
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }
    else throw new NullPointerException("null params !");
}

/**
 * 将时间戳转换成标准年月日的工具类
 * Time:long时间戳
 * time:yyyy-MM-dd HH:mm:ss的格式
 * */
    public static String StringParseTime(String Time){
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(Time);
        Date date = new Date(lt);
        time= simpleDateFormat.format(date);
        return time;
    }
}
