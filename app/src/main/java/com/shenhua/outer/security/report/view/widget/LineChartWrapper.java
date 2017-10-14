package com.shenhua.outer.security.report.view.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * 作者：nodlee/1516lee@gmail.com
 * 时间：10/14 0014
 * 说明：折线图工具类
 * <p>
 * 修改: shenhua
 */
public class LineChartWrapper {

    private LineChart mLineChart;
    private LineDataSet mLineDataSet;
    private static final int LABEL_STEP = 5;

    public LineChartWrapper(LineChart lineChart) {
        this.mLineChart = lineChart;
    }

    public LineChartWrapper create(List<Entry> entryList) {
        if (entryList != null && !entryList.isEmpty()) {
            mLineChart.setScaleEnabled(false);
            mLineChart.setDrawBorders(false);
            mLineChart.setDescription(null);
            mLineChart.setHighlightPerTapEnabled(true);
            mLineChart.getAxisRight().setEnabled(false);
            mLineChart.getLegend().setEnabled(false);
            mLineChart.animateY(2000);
            setupXAxis(mLineChart.getXAxis());
            setupYAxis(mLineChart.getAxisLeft());
            mLineDataSet = setupDataSet(entryList);
            mLineChart.setData(new LineData(mLineDataSet));
        } else {
            mLineChart.setNoDataText("暂无记录");
        }
        return this;
    }

    public LineChart create(List<Entry> entryList1, List<Entry> entryList2) {
        if ((entryList1 == null && entryList2 == null)
                || (entryList1.isEmpty() && entryList2.isEmpty())) {
            mLineChart.setNoDataText("暂无记录");
        } else {
            mLineChart.setScaleEnabled(false);
            mLineChart.getAxisRight().setEnabled(false);
            mLineChart.getLegend().setEnabled(false);
            setupXAxis(mLineChart.getXAxis());
            setupYAxis(mLineChart.getAxisLeft());
            LineDataSet lineDataSet1 = setupDataSet(entryList1);
            LineDataSet lineDataSet2 = setupDataSet(entryList2);
            LineData lineData = setupLineData(lineDataSet1, lineDataSet2);
            mLineChart.setData(lineData);
        }
        return mLineChart;
    }

    /**
     * set the max value for the yAxis.
     *
     * @param value max value
     */
    public LineChartWrapper setYTop(int value) {
        mLineChart.getAxisLeft().setAxisMaximum(value);
        return this;
    }

    /**
     * set the default max value for the yAxis by calculate.
     */
    public LineChartWrapper setDefaultYTop() {
        int max = (int) mLineChart.getLineData().getYMax();
        mLineChart.getAxisLeft().setAxisMaximum(getMax(max));
        return this;
    }

    public LineChartWrapper setXLabelCount(int count) {
        mLineChart.getXAxis().setLabelCount(count, true);
        return this;
    }

    public LineChartWrapper setCenterAxisLabels(boolean b) {
        mLineChart.getXAxis().setCenterAxisLabels(b);
        return this;
    }

    /**
     * if set to true, the chart will avoid that the first and last label entry
     * in the chart "clip" off the edge of the chart or the screen
     *
     * @param enabled default true,set false that the chart can scroll left and right
     */
    public LineChartWrapper setXAvoidFirstLastClipping(boolean enabled) {
        mLineChart.getXAxis().setAvoidFirstLastClipping(enabled);
        return this;
    }

    public LineChartWrapper setXLabelAngle(float angle) {
        mLineChart.getXAxis().setLabelRotationAngle(angle);
        return this;
    }

    public LineChartWrapper setMarkView(IMarker marker) {
        mLineChart.setMarker(marker);
        return this;
    }

    public LineChartWrapper setXValueFormat(IAxisValueFormatter f) {
        mLineChart.getXAxis().setValueFormatter(f);
        return this;
    }

    public LineChartWrapper setLineColor(int color) {
        mLineDataSet.setColor(color);
        return this;
    }

    public LineChartWrapper setShadowColor(int color) {
        mLineDataSet.setFillDrawable(new ColorDrawable(color));
        return this;
    }

    public LineChartWrapper setShadowDrawable(Drawable drawable) {
        mLineDataSet.setFillDrawable(drawable);
        return this;
    }

    public LineChartWrapper setDashLineColor(int color) {
        mLineDataSet.setHighLightColor(color);
        return this;
    }

    private LineDataSet setupDataSet(List<Entry> entryList) {
        LineDataSet lineDataSet = new LineDataSet(entryList, "");
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.BLUE);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.1f);
        lineDataSet.enableDashedHighlightLine(5, 5, 0);
        lineDataSet.setFillDrawable(new ColorDrawable(0x7FF79856));
        lineDataSet.setColor(0xFFF2945A);
        return lineDataSet;
    }

    private LineData setupLineData(LineDataSet... dataSet) {
        return new LineData(dataSet);
    }

    private void setupXAxis(XAxis xAxis) {
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(true);
        setupAxis(xAxis);
    }

    private void setupYAxis(YAxis yAxis) {
        yAxis.setAxisMinimum(0f);
        yAxis.setLabelCount(LABEL_STEP);
        yAxis.setDrawZeroLine(false);
        setupAxis(yAxis);
    }

    private void setupAxis(AxisBase base) {
        base.setAxisLineColor(0xFFAFAFAF);
        base.setDrawAxisLine(true);
        base.setDrawGridLines(false);
        base.setDrawLabels(true);
    }

    public static int getMax(int a) {
        if (a < LABEL_STEP) {
            return LABEL_STEP;
        } else if (a % LABEL_STEP == 0) {
            return a;
        } else {
            return (a / LABEL_STEP + 1) * LABEL_STEP;
        }
    }

}
