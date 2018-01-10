package com.example.android.search;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundedBackgroundSpan2 extends ReplacementSpan {

    private static final int CORNER_RADIUS = 28;

    private static final float PADDING_X = GeneralUtils.convertDpToPx(25);
    private static final float PADDING_Y = GeneralUtils.convertDpToPx(2);

    private static final float MAGIC_NUMBER = GeneralUtils.convertDpToPx(2);

    private int mBackgroundColor;
    private int mTextColor;
    private float mTextSize;

    /**
     * @param backgroundColor color value, not res id
     * @param textSize        in pixels
     */
    public RoundedBackgroundSpan2(int backgroundColor, int textColor, float textSize) {
        mBackgroundColor = backgroundColor;
        mTextColor = textColor;
        mTextSize = textSize;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint = new Paint(paint); // make a copy for not editing the referenced paint

        paint.setTextSize(mTextSize);
        Paint strokePaint = new Paint();
// stroke
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(6);

        // Draw the rounded background
        paint.setColor(mBackgroundColor);
        float textHeightWrapping = GeneralUtils.convertDpToPx(4);
        float tagBottom = top + textHeightWrapping + PADDING_Y + mTextSize + PADDING_Y + textHeightWrapping;
        float tagRight = x + getTagWidth(text, start, end, paint);
        RectF rect = new RectF(x+5, top-5, tagRight + 40, tagBottom +20);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, strokePaint);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);

        // Draw the text
        paint.setColor(mTextColor);
        canvas.drawText(text, start, end, x + 20, tagBottom - PADDING_Y - textHeightWrapping - MAGIC_NUMBER, paint);
    }

    private int getTagWidth(CharSequence text, int start, int end, Paint paint) {
        return Math.round(PADDING_X + paint.measureText(text.subSequence(start, end).toString()) + PADDING_X);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        paint = new Paint(paint); // make a copy for not editing the referenced paint
        paint.setTextSize(mTextSize);
        return getTagWidth(text, start, end, paint);
    }
}