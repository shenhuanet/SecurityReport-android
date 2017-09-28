package com.shenhua.outer.security.report.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.shenhua.outer.security.report.R;

import java.util.List;

/**
 * 作者：nodlee/1516lee@gmail.com
 * 时间：10/14 0014
 * 说明：折线图工具类
 * <p>
 * 修改: shenhua
 */
public class LineChartWrapper {

    private Context mContext;
    private LineChart mLineChart;

    public LineChartWrapper(Context context, LineChart lineChart) {
        mContext = context;
        this.mLineChart = lineChart;
    }

    public LineChart create(List<Entry> entryList) {
        if (entryList != null && !entryList.isEmpty()) {
            mLineChart.setMarker(new LineMarkView(mContext, R.layout.view_custom_marker));
            mLineChart.setScaleEnabled(false);
            mLineChart.setDrawBorders(false);
            mLineChart.setDescription(null);
            mLineChart.setHighlightPerTapEnabled(true);
            mLineChart.getAxisRight().setEnabled(false);
            mLineChart.getLegend().setEnabled(false);
            setupXAxis(mLineChart.getXAxis());
            setupYAxis(mLineChart.getAxisLeft());
            mLineChart.animateY(2000);
            LineDataSet lineDataSet = setupDataSet(entryList);
            LineData lineData = setupLineData(lineDataSet);
            mLineChart.setData(lineData);
        } else {
            mLineChart.setNoDataText("暂无记录");
        }
        return mLineChart;
    }

    public LineChart create(List<Entry> entryList1, List<Entry> entryList2) {
        if ((entryList1 == null && entryList2 == null)
                || (entryList1.isEmpty() && entryList2.isEmpty())) {
            mLineChart.setNoDataText("暂无记录");
            mLineChart.setNoDataTextColor(Color.parseColor("#ffffff"));
        } else {
            mLineChart.setMarker(new LineMarkView(mContext, R.layout.view_custom_marker));
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

    private LineDataSet setupDataSet(List<Entry> entryList) {
        LineDataSet lineDataSet = new LineDataSet(entryList, "");
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.GREEN);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.1f);
        lineDataSet.enableDashedHighlightLine(5, 5, 0);
        lineDataSet.setFillDrawable(new ColorDrawable(ContextCompat.getColor(mContext, R.color.colorOrangeHalf)));
        lineDataSet.setColor(ContextCompat.getColor(mContext, R.color.colorOrange1));
        return lineDataSet;
    }

    private LineData setupLineData(LineDataSet... dataSet) {
        return new LineData(dataSet);
    }

    private void setupXAxis(XAxis xAxis) {
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelCount(24, true);
        setupAxis(xAxis);
    }

    private void setupYAxis(YAxis yAxis) {
        yAxis.setAxisMinimum(0f);
        yAxis.setLabelCount(5);
        yAxis.setDrawZeroLine(false);
        setupAxis(yAxis);
    }

    private void setupAxis(AxisBase base) {
        base.setAxisLineColor(R.color.colorWhiteLite);
        base.setDrawAxisLine(true);
        base.setDrawGridLines(false);
        base.setDrawLabels(true);
    }
}
