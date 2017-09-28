package com.shenhua.outer.security.report.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.shenhua.outer.security.report.R;

/**
 * Created by shenhua on 2017/9/26.
 * Email shenhuanet@126.com
 */
public class DashedLine extends View {

    Paint paint = new Paint();
    Path path = new Path();
    PathEffect effects = new DashPathEffect(new float[]{14, 14, 14, 14}, 2);

    public DashedLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorGray1));
        path.moveTo(0, 0);
        path.lineTo(0, 160);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
}