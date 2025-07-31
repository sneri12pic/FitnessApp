package com.example.fitnessapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class StrokeTextView extends AppCompatTextView {

    private Paint strokePaint;

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(6); // stroke thickness
        strokePaint.setColor(Color.parseColor("#4F2912")); // stroke color
        strokePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Set stroke paint text size and typeface to match original
        strokePaint.setTextSize(getTextSize());
        strokePaint.setTypeface(getTypeface());

        // Draw stroke first
        canvas.drawText(getText().toString(), getPaddingLeft(), getBaseline(), strokePaint);

        // Set fill color
        setTextColor(Color.parseColor("#F5DBCB"));

        // Then draw the original (fill) text
        super.onDraw(canvas);
    }
}
