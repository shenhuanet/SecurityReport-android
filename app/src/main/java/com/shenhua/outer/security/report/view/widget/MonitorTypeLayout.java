package com.shenhua.outer.security.report.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenhua.outer.security.report.R;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class MonitorTypeLayout extends RelativeLayout {

    public MonitorTypeLayout(Context context, String key, String value) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_monitor_type, null);
        addView(view);

        TextView keyTv = (TextView) view.findViewById(R.id.tvKey);
        TextView valueTv = (TextView) view.findViewById(R.id.tvValue);
        keyTv.setText(key);
        valueTv.setText(value);
    }
}
