package com.example.fitnessapp;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class GaugeView extends View {

    private Paint paintArc, paintProgress, paintStroke;
    private float percent = 0f;

    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintArc.setStyle(Paint.Style.STROKE);
        paintArc.setStrokeWidth(50);
        paintArc.setStrokeCap(Paint.Cap.ROUND);

        paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setStrokeWidth(6);
        paintStroke.setColor(Color.parseColor("#4F2912"));
        paintStroke.setTextAlign(Paint.Align.LEFT);

        paintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintProgress.setColor(Color.parseColor("#FF784F"));
        paintProgress.setAlpha(50);
        paintProgress.setStyle(Paint.Style.STROKE);
        paintProgress.setStrokeWidth(25);
        paintProgress.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setPercent(float percent) {
        this.percent = Math.min(percent, 1f);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float centerX = getWidth() / 2f;
        float centerY = getHeight();
        float radius = Math.min(centerX, centerY) - 20;

        RectF rect = new RectF(centerX - radius, centerY - radius,
                centerX + radius, centerY + radius);

        /*float centerXStroke = (getWidth() / 2f) -20;
        float centerYStroke = getHeight() - 20;

        RectF rectStroke = new RectF(centerXStroke - radius, centerYStroke - radius,
                centerXStroke + radius, centerYStroke + radius);*/

        Shader shader = new SweepGradient(centerX, centerY,
                new int[]{Color.GREEN, Color.YELLOW, Color.RED},
                new float[]{0f, 0.25f, 1f});
        Matrix rotateMatrix = new Matrix();
        rotateMatrix.setRotate(180, centerX, centerY);
        shader.setLocalMatrix(rotateMatrix);
        paintArc.setShader(shader);

        canvas.drawArc(rect, 180, 180, false, paintArc);
        canvas.drawArc(rect, 180, 180 * percent, false, paintProgress);
        /*canvas.drawArc(rectStroke, 180, 180, false, paintStroke);*/

        if (percent > 0f) {
            // Calculate angle in radians
            float angleDeg = 180 + (180 * percent); // Starts from 180° (left), moves clockwise
            double angleRad = Math.toRadians(angleDeg);

            // Coordinates on arc
            float x = centerX + (float) (radius * Math.cos(angleRad));
            float y = centerY + (float) (radius * Math.sin(angleRad));

            // Draw the point
            Paint pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            pointPaint.setColor(Color.BLACK); // or any color
            canvas.drawCircle(x, y, 10, pointPaint); // radius = 10px
        }

    }
}
