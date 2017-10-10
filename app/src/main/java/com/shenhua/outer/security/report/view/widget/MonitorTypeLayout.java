package com.shenhua.outer.security.report.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenhua.outer.security.report.R;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class MonitorTypeLayout extends RelativeLayout {

    public MonitorTypeLayout(Context context, int type, int warningTotal, String readValue) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_monitor_type, null);
        addView(view);

        TextView keyTv = (TextView) view.findViewById(R.id.tvKey);
        TextView valueTv = (TextView) view.findViewById(R.id.tvValue);
        ImageView statusIv = (ImageView) view.findViewById(R.id.ivStationState);

        String[] typeKey = getResources().getStringArray(R.array.monitor_type);
        keyTv.setText(typeKey[type - 1]);
//        valueTv.setText(TextUtils.isEmpty(readValue) ? "" : readValue);
        setValue(valueTv, TextUtils.isEmpty(readValue) ? "" : readValue, type);
        setStatusLevel(statusIv, warningTotal);
        setTextColor(valueTv, warningTotal);
    }

    private void setValue(TextView textView, String value, int type) {
        String[] typeUnit = getResources().getStringArray(R.array.monitor_type_unit);
        textView.setText(value);
        switch (type) {
            case 2:
            case 3:
            case 6:
            case 7:
            case 8:
                textView.append(typeUnit[type - 1]);
                break;
        }
    }

    private void setStatusLevel(ImageView imageView, int value) {
        imageView.setImageLevel(value);
//        try {
//
//            TypedArray ar = getResources().obtainTypedArray(R.array.warning_type_bg);
//            int len = ar.length();
//            int[] resIds = new int[len];
//            for (int i = 0; i < len; i++)
//                resIds[i] = ar.getResourceId(i, 0);
//            ar.recycle();
//            valueTv.setBackgroundResource(resIds[value]);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void setTextColor(TextView valueTv, int value) {
        try {
            TypedArray ar = getResources().obtainTypedArray(R.array.warning_type_color);
            int len = ar.length();
            int[] resIds = new int[len];
            for (int i = 0; i < len; i++)
                resIds[i] = ar.getResourceId(i, 0);
            ar.recycle();
            valueTv.setTextColor(ContextCompat.getColor(getContext(), resIds[value]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
