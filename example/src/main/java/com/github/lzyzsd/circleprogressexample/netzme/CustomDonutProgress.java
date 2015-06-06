package com.github.lzyzsd.circleprogressexample.netzme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.github.lzyzsd.circleprogress.DonutProgress;

/**
 * Created by daori on 6/6/15.
 */
public class CustomDonutProgress extends DonutProgress {
    public CustomDonutProgress(Context context) {
        super(context);
    }

    public CustomDonutProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDonutProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initPainters() {
        super.initPainters();
        finishedPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float delta = Math.max(finishedStrokeWidth, unfinishedStrokeWidth);
        finishedOuterRect.set(delta,
                delta,
                getWidth() - delta,
                getHeight() - delta);
        unfinishedOuterRect.set(delta,
                delta,
                getWidth() - delta,
                getHeight() - delta);

        int finishStartAngle = 269;// dimulai dari 270 derajat karena 0 derajat sama dengan mulai pada jam 3
        int unFinishStartAngle = 360 + finishStartAngle;

        float innerCircleRadius = (getWidth() - Math.min(finishedStrokeWidth, unfinishedStrokeWidth) + Math.abs(finishedStrokeWidth - unfinishedStrokeWidth)) / 2f;
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, innerCircleRadius, innerCirclePaint);
        canvas.drawArc(unfinishedOuterRect, getProgressAngle(),  unFinishStartAngle - getProgressAngle(), false, unfinishedPaint);
        canvas.drawArc(finishedOuterRect, finishStartAngle, getProgressAngle(), false, finishedPaint);
    }
}
