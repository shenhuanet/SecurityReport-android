package com.shenhua.outer.security.report.view.widget;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by shenhua on 2017-10-13-0013.
 * Email shenhuanet@126.com
 */
public class StringXFormatter implements IAxisValueFormatter {

    private List<String> mValues;

    public StringXFormatter(List<String> mValues) {
        this.mValues = mValues;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = (int) (value % mValues.size());
//        if (index == 0) {
//            return "";
//        }
        return mValues.get(index);
    }
}
