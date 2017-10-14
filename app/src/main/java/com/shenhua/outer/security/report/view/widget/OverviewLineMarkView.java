package com.shenhua.outer.security.report.view.widget;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.shenhua.outer.security.report.R;

/**
 * used for the overview fragment's linechart.
 * <p>
 * Created by shenhua on 2017-09-28-0028.
 * Email shenhuanet@126.com
 */
public class OverviewLineMarkView extends BaseMarkerView {

    private TextView tvContent;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context        context
     * @param chart          使用时需要传入LineChart对象
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public OverviewLineMarkView(Context context, Chart chart, int layoutResource) {
        super(context, chart, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(String.valueOf((int) e.getY()));
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    @Override
    public MPPointF getOffsetRight() {
        return new MPPointF(-getWidth(), -getHeight());
    }

}
