package com.example.lily.animationpractice.property;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by ljq
 * on 2018/6/11.
 */

public class BerzierI3View extends View {




    private PointF control1, control2;
    private PointF start,end;
    private Paint paint,pointPaint;
    private Path path;

    public float Base = 200.0f;





    public BerzierI3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;
    }


    void initView(){
        initPoint();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setStrokeWidth(20);
        pointPaint.setColor(Color.WHITE);

        path = new Path();
            ValueAnimator  animator = ValueAnimator.ofFloat(-Base,Base);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float frag = (float) animation.getAnimatedValue();
                    control1.x = (float) (Base*0.5f*Math.sin(frag*Math.PI/Base));
                    control1.y = 0.8f*frag;

                }
            });



        ValueAnimator  animator2 = ValueAnimator.ofFloat(Base,-Base);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float frag = (float) animation.getAnimatedValue();
                control2.x = (float) (Base*0.5f*Math.sin(frag*Math.PI/Base));
                control2.y = 1.2f*frag;
                invalidate();

            }
        });


        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.play(animator).with(animator2);
        set.start();

    }

    private int screenW, screenH;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = Math.min(screenW, screenH);
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = Math.min(screenW, screenH);
        }

        setMeasuredDimension(widthSize, heightSize);

    }
    private int widht, height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widht = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(widht / 2, height / 2);

        canvas.drawColor(Color.GRAY);
        canvas.drawPoint(start.x,start.y,pointPaint);
        canvas.drawPoint(end.x,end.y,pointPaint);
        canvas.drawPoint(control1.x,control1.y,pointPaint);
        canvas.drawPoint(control2.x,control2.y,pointPaint);

        path.reset();
        path.moveTo(start.x,start.y);
        path.cubicTo(control1.x,control1.y,control2.x,control2.y,end.x,end.y);
        canvas.drawPath(path,paint);


    }



    private void initPoint(){

        /*width = getWidth();
        height = getHeight();*/
        start = new PointF(-Base,0);
        end = new PointF(Base,0);
        control1 = new PointF(-Base/2,-Base);
        control2 = new PointF(Base/2,Base);


    }

}
