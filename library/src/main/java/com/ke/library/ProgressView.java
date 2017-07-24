package com.ke.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.view.View;


public class ProgressView extends View {
    private int mMaxProgress;
    private int mProgress;
    private int mCircleBgColor;
    private int mProgressColor;
    private float mRadius;
    private float mProgressWidth;
    private float mPadding;

    private Paint mPaint;
    private RectF mRectF;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mMaxProgress = typedArray.getInt(R.styleable.ProgressView_maxProgress, 100);
        mProgress = typedArray.getInt(R.styleable.ProgressView_progress, 50);
        mCircleBgColor = typedArray.getColor(R.styleable.ProgressView_circleColor, Color.parseColor("#DDDDDD"));
        mProgressColor = typedArray.getColor(R.styleable.ProgressView_progressColor, Color.RED);
        mRadius = typedArray.getDimension(R.styleable.ProgressView_radius, 100);
        mProgressWidth = typedArray.getDimension(R.styleable.ProgressView_progressWidth, 5);
        mPadding = typedArray.getDimension(R.styleable.ProgressView_contentPadding, 0);
        typedArray.recycle();


        initPaint();

    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
//        mPaint.setColor(mCircleBgColor);//默认进度条颜色
        mPaint.setStyle(Paint.Style.STROKE);//设置空心样式
        mPaint.setStrokeWidth(mProgressWidth);//设置圆环宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND);//线条圆头
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int) (mRadius + mProgressWidth / 2 + mPadding) * 2;

        int measureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        super.onMeasure(measureSpec, measureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        mPaint.setColor(mCircleBgColor);
        canvas.drawCircle(centerX, centerY, mRadius, mPaint);


        if (mRectF == null) {
            mRectF = new RectF(centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);
        }
        mPaint.setColor(mProgressColor);
        canvas.drawArc(mRectF, -90, 360 * mProgress / mMaxProgress, false, mPaint);

    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    @UiThread
    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    @UiThread
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public int getCircleBgColor() {
        return mCircleBgColor;
    }

    @UiThread
    public void setCircleBgColor(int circleBgColor) {
        mCircleBgColor = circleBgColor;
        invalidate();
    }

    public int getProgressColor() {
        return mProgressColor;
    }

    @UiThread
    public void setProgressColor(int progressColor) {
        mProgressColor = progressColor;
        invalidate();
    }


}
