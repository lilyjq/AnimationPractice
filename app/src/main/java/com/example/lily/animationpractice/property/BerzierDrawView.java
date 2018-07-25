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
import android.view.View;

/**
 * Created by ljq
 * on 2018/6/14.
 */

public class BerzierDrawView extends View {

    private Paint paint;
    private PointF start1,end1;
    private PointF start2,end2;
    private PointF center1,center2;
    private int distance = 200;
    private float raduis1,raduis2;
    private Path path;
    private PointF control;







    public BerzierDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        raduis1 = 25;
        raduis2 = 25;
        center1 = new PointF(0,0);


        start1 = new PointF(0,-25);
        end1 = new PointF(0,25);

        initPoint();

        path = new Path();

        ValueAnimator animator = ValueAnimator.ofInt(-200,200);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                distance = (int) animation.getAnimatedValue();
                center2.x = distance;
                float fraction = animation.getAnimatedFraction();
                if(fraction<0.2){
                    fraction = 0.2f;
                }
                if(fraction>0.8){
                    fraction = 0.8f;
                }
                fraction = (fraction>0.5)?(1-fraction):fraction;
                raduis2 = raduis1*fraction*2;
                initPoint();
                invalidate();


            }
        });
        animator.start();


    }

    private void initPoint() {

        center2 = new PointF(distance ,0);
        control = new PointF(distance/2,0);

        start2 = new PointF(distance,raduis2);
        end2 = new PointF(distance,-raduis2);
    }


    private  int width,height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();

        canvas.translate(width/2,height/2);

        canvas.drawCircle(center1.x,center1.y,raduis1,paint);
        canvas.drawCircle(center2.x,center2.y,raduis2,paint);

        path.moveTo(start1.x,start1.y);
        path.quadTo(control.x,control.y,end2.x,end2.y);
        path.lineTo(start2.x,start2.y);
        path.quadTo(control.x,control.y,end1.x,end1.y);
        path.close();
        canvas.drawPath(path,paint);


    }
}
