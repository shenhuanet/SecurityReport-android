package com.ldf.calendar.listener;

import android.graphics.Canvas;

import com.ldf.calendar.view.Day;

/**
 * 自定义日历渲染模式
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public interface IDayRenderer {

    void refreshContent();

    void drawDay(Canvas canvas, Day day);

    IDayRenderer copy();

}
