package com.shenhua.outer.security.report.view.widget;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by shenhua on 2017/10/14.
 * Email shenhuanet@126.com
 */
public class XValueFormatter implements IAxisValueFormatter {

    private List<String> mXValue;

    public XValueFormatter(List<String> mXValue) {
        this.mXValue = mXValue;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (value < 0) {
            return "";
        } else {
            int index = (int) (value % mXValue.size());
            return mXValue.get(index);
        }
    }
}
