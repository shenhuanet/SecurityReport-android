package com.shenhua.outer.security.report.core.utils;

import android.content.Context;

import com.shenhua.outer.security.report.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class AndroidUtils {

    private static final String[] WARNING_TYPE = {"正常", "警告", ""};

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

    public static String formatWarningType(Context context, int value) {
        String[] str = context.getResources().getStringArray(R.array.warning_type);
        return str[value];
    }

    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return dateFormat.format(new Date(time));
    }
}
