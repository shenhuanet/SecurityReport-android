package com.shenhua.outer.security.report.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shenhua.outer.security.report.R;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class BaseItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight = 1;
    private Paint dividerPaint;

    public BaseItemDecoration(Context context) {
        dividerPaint = new Paint();
        dividerPaint.setColor(ContextCompat.getColor(context, R.color.colorWindowBackground));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }

    }
}
