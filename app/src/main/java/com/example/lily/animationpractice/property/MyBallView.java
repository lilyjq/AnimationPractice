package com.example.lily.animationpractice.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ljq
 * on 2018/6/5.
 */

public class MyBallView extends View {



    private MyPoint currentPoint;
    private Paint paint;

    public MyBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.MAGENTA);
    }

    private String color;

    public void setColor(String color){
        this.color  = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    public String getColor(){
        return color;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(currentPoint == null){
            currentPoint = new MyPoint(50,50);
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }
    }


    private void drawCircle(Canvas canvas){
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,50,paint);

    }


    private void startAnimation(){
        MyPoint start = new MyPoint(50,50);
        MyPoint end = new MyPoint(getWidth()-50,getHeight()-50);
        ValueAnimator animator = ValueAnimator.ofObject(new MyPointEvaluator(),start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (MyPoint) animation.getAnimatedValue();
                invalidate();
            }
        });
        //animator.setDuration(3000);
        //animator.start();

        ObjectAnimator animator1 = ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#0000FF", "#FF0000");
       // animator1.setDuration(3000);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.play(animator).with(animator1);
        set.start();
    }
}
