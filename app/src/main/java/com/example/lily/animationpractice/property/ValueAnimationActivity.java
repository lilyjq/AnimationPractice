package com.example.lily.animationpractice.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/6/4.
 */

public class ValueAnimationActivity extends AppCompatActivity {

    ImageView ball;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanimation);
        ball = findViewById(R.id.iv_ball);
    }

    public void parabola(View view) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0,0));
        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF f = new PointF();
                f.x = 200*fraction*3;
                f.y = 0.5f*200*(fraction*3)*(fraction*3);

                return f;
            }
        });
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               PointF f = (PointF) animation.getAnimatedValue();
                ball.setTranslationY(f.y);
                ball.setTranslationX(f.x);
            }
        });



    }

    public void vertical(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0,getHeight()-view.getHeight());
        animator.setDuration(1800);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentV = (float) animation.getAnimatedValue();
                ball.setTranslationY(currentV);
            }
        });
    }

    private float getHeight(){

        return  getResources().getDisplayMetrics().heightPixels;
       // return  getWindowManager().getDefaultDisplay().getWidth();
    }

    private float getWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.widthPixels;
    }

    public void MoveView(View view){
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0,0));
        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF f = new PointF();
                f.x = 200*fraction*3;
                f.y = 0.5f*200*(fraction*3)*(fraction*3);

                return f;
            }
        });
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF f = (PointF) animation.getAnimatedValue();
                ball.setTranslationY(f.y);
                ball.setTranslationX(f.x);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup parentView = (ViewGroup) ball.getParent();
                if(parentView!= null)
                parentView.removeView(ball);


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
     /*   animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup parentView = (ViewGroup) ball.getParent();
                if(parentView!= null)
                    parentView.removeView(ball);

            }
        });*/
    }


}
