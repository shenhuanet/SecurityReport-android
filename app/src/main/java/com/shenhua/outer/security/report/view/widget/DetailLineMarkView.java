package com.shenhua.outer.security.report.view.widget;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.shenhua.outer.security.report.R;

import java.util.List;

/**
 * Created by shenhua on 2017-09-28-0028.
 * Email shenhuanet@126.com
 */
public class DetailLineMarkView extends BaseMarkerView {

    private TextView tvX;
    private TextView tvY;
    private List<String> xVaules;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context        context
     * @param chart          使用时需要传入LineChart对象
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public DetailLineMarkView(Context context, Chart chart, int layoutResource) {
        super(context, chart, layoutResource);
        tvX = (TextView) findViewById(R.id.tvX);
        tvY = (TextView) findViewById(R.id.tvY);
    }

    public DetailLineMarkView setxVaules(List<String> xVaules) {
        this.xVaules = xVaules;
        return this;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (xVaules != null) {
            tvX.setText(xVaules.get((int) e.getX()));
        } else {
            tvX.setText(String.valueOf((int) e.getX()));
        }
        tvY.setText(String.valueOf((int) e.getY()));
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
