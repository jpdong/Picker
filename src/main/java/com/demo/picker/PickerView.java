package com.demo.picker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by dong on 2017/12/26 0026.
 */

public class PickerView extends View {

    String[] mItems = new String[10];
    Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mTextSize = 20;
    private int mTextHeight;
    private int mTextOffset;
    private int mCurrentIndex = 0;

    public PickerView(Context context) {
        this(context, null, 0);
    }

    public PickerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        loadData();
    }

    private void loadData() {
        for (int i = 0; i < 10; i++) {
            mItems[i] = "item " + i;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getWidth(widthMeasureSpec), getHeight(heightMeasureSpec));
    }

    private int getWidth(int widthMeasureSpec) {
        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (measureMode) {
            case MeasureSpec.AT_MOST:
                return getSelfWidth();
            case MeasureSpec.EXACTLY:
                return Math.max(getSelfWidth(), measureSize);

        }
        return 0;
    }

    private int getSelfWidth() {
        return (int) mTextPaint.measureText(mItems[0]);
    }

    private int getHeight(int heightMeasureSpec) {
        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (measureMode) {
            case MeasureSpec.AT_MOST:
                return getSelfHeight();
            case MeasureSpec.EXACTLY:
                return Math.max(getSelfHeight(), measureSize);
        }
        return 0;
    }

    private int getSelfHeight() {
        mTextHeight = sp2px(mTextSize);
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        mTextOffset = fontMetricsInt.bottom;
        return 5 * mTextHeight;
    }

    private int sp2px(int mTextSize) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getDisplay().getMetrics(displayMetrics);
        return (int) (mTextSize * displayMetrics.scaledDensity);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mItems[mCurrentIndex],0,mTextOffset,mTextPaint);
    }
}
