package com.example.lily.animationpractice.property;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ljq
 * on 2018/6/11.
 */

public class BezierView extends View {

    //二阶贝塞尔曲线

    private Paint paint;
    private Paint pointPaint;
    private Path path;
    private PointF startPoint, endPoint ,controlPoint;
    private ValueAnimator animator;


    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();

    }


    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      //  pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(Color.GREEN);
        pointPaint.setStrokeWidth(20);

        path = new Path();

    //    initPoint();

       /* animator = new ValueAnimator();
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                controlPoint.x =  controlPoint.x+10;
                controlPoint.y = controlPoint.y+10;
                invalidate();

            }
        });没有用
        */


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPoint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBezier(canvas);
    }



    private void drawBezier(Canvas canvas){

        path.reset();
        canvas.drawColor(Color.GRAY);
        canvas.drawPoint(startPoint.x,startPoint.y,pointPaint);
        canvas.drawPoint(endPoint.x,endPoint.y,pointPaint);
        canvas.drawPoint(controlPoint.x,controlPoint.y,pointPaint);


        path.moveTo(startPoint.x,startPoint.y);
        path.quadTo(controlPoint.x,controlPoint.y,endPoint.x,endPoint.y);

        canvas.drawPath(path,paint);
       // path.close();


    }

    private void initPoint(){
        int x = getWidth()/2;
        int left = getWidth()/4;
        int right = left*3;
        int y = getHeight()/2;
        startPoint = new PointF(left,y);
        endPoint = new PointF(right,y);
        controlPoint = new PointF(x ,getHeight()/10);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float height = event.getY();
        float wedith = event.getX();

        controlPoint.x = wedith;
        controlPoint.y = height;
        invalidate();
        return true;

    }
}
