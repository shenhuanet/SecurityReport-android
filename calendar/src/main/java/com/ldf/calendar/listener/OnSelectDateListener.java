package com.ldf.calendar.listener;

import com.ldf.calendar.component.CalendarDate;

/**
 * 日期选择监听
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public interface OnSelectDateListener {
    void onSelectDate(CalendarDate date);

    void onSelectOtherMonth(int offset);//点击其它月份日期
}
