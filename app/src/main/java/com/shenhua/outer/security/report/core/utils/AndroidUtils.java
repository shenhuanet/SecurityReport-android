package com.shenhua.outer.security.report.core.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.ldf.calendar.component.CalendarDate;
import com.shenhua.outer.security.report.R;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class AndroidUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context context
     * @param pxValue px
     * @return dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String formatWarningStatus(Context context, int value) {
        try {
            String[] str = context.getResources().getStringArray(R.array.warning_status);
            return str[value - 1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatWarningType(Context context, int value) {
        try {
            String[] str = context.getResources().getStringArray(R.array.warning_type);
            return str[value];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return dateFormat.format(new Date(time));
    }

    public static String fitTwoLenght(String str) {
        if (!TextUtils.isEmpty(str) && str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    public static String formatData(CalendarDate date) {
        return date.getYear() + "-"
                + fitTwoLenght(String.valueOf(date.getMonth())) + "-"
                + fitTwoLenght(String.valueOf(date.getDay()));
    }

    public static ArrayList<String> ConvertObjToList(Object obj) {
        ArrayList<String> list = new ArrayList<>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                try {
                    Field f = obj.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    list.add(o.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void showEmptyLoading(TextView emptyView) {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText("数据加载中");
    }

    public static void showEmptyError(TextView emptyView) {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText("数据错误");
    }

    public static void showEmptyNull(TextView emptyView) {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText("数据为空");
    }

    public static void hideEmpty(TextView emptyView) {
        emptyView.setVisibility(View.GONE);
    }

    public static void hideKeyboard(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && context.getCurrentFocus() != null) {
            if (context.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
