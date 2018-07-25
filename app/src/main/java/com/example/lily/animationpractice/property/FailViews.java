package com.example.lily.animationpractice.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.CycleInterpolator;

/**
 * Created by ljq
 * on 2018/6/6.
 */

public class FailViews extends View {

    private Paint circlePaint;
    private Paint linePaint;
    private float width_stoke = 10;
    private float centerX,centerY;
    private float mdegree =0 ;
    private float first_incre=0,second_incre=0;
    private ValueAnimator circle_animator,first_animator,second_animator;
    float raduis;


    public FailViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();

    }

    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.RED);
        circlePaint.setStrokeWidth(width_stoke);
        circlePaint.setStrokeJoin(Paint.Join.ROUND);
        circlePaint.setStyle(Paint.Style.STROKE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(width_stoke);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    //先不考虑padding margin
    private void measurecenter(){
        //拿到直径,and 获取中心点坐标
        float  diameter= Math.min(getWidth(),getHeight());
        raduis = diameter/2-20;
        centerX = diameter/2;
        centerY = diameter/2;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measurecenter();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPaint(canvas);
    }

    private void drawPaint(Canvas canvas){

        RectF f = new RectF();
        f.top = centerY- raduis;
        f.bottom = centerY+raduis;
        f.left = centerX - raduis;
        f.right =centerX+raduis;

        canvas.drawArc(f,0,mdegree,false,circlePaint);
        canvas.drawLine(centerX-raduis/2,centerY-raduis/2,centerX-raduis/2+first_incre,centerY-raduis/2+first_incre,linePaint);
        canvas.drawLine(centerX+raduis/2,centerY-raduis/2,centerX+raduis/2-second_incre,centerY-raduis/2+second_incre,linePaint);
        startAnimation();
    }



    private void startAnimation(){
        if(set!=null ){
            return;
        }

        circle_animator = ValueAnimator.ofInt(0,360);

        circle_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int fraction = (Integer) animation.getAnimatedValue();
                mdegree = fraction;
                invalidate();
            }
        });
        circle_animator.setDuration(1800);
        first_animator = ValueAnimator.ofFloat(0,raduis);
        first_animator.setDuration(900);
        second_animator = ValueAnimator.ofFloat(0,raduis);
        second_animator.setDuration(900);
        first_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                first_incre = fraction;
                invalidate();

            }
        });
        second_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                second_incre = fraction;
                invalidate();
            }
        });


        set = new AnimatorSet();
        set.play(circle_animator).before(first_animator);
        set.play(second_animator).after(first_animator);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startEndAnimation();
            }
        });

    }




    AnimatorSet set;


    private void startEndAnimation(){
        int currentX = (int) this.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"x",currentX+20);
        animator.setInterpolator(new CycleInterpolator(3));
        animator.start();
    }






}
