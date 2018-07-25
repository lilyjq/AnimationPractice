package com.example.lily.animationpractice.property;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lily.animationpractice.R;


/**
 * Created by ljq
 * on 2018/5/30.
 */

public class PropertyAnimationActivity extends AppCompatActivity{

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributeanimation);
        button = findViewById(R.id.button);
//失败


    }

    public void annimation(final View view) {
       // ObjectAnimation(view);
        propertyValueHolder(view);

    }

    private void ObjectAnimation(View view){
        //rotationY,x,y,scaleX,scaleY,alpha
        ObjectAnimator.ofFloat(view,"rotationX",1.0F,0.5F).setDuration(800).start();
    }

    private void ObjectAnimationOther(final View view){

        @SuppressLint("ObjectAnimatorBinding")
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"qq",1.0F,0.5F).setDuration(800);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                view.setAlpha(currentValue);
                view.setScaleX(currentValue);
                view.setScaleY(currentValue);

            }
        });
    }


    private void propertyValueHolder(View view){
        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("rotationX",0.0F,180.0F);
        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("rotationY",0.0F,180.0F);
        PropertyValuesHolder a = PropertyValuesHolder.ofFloat("alpha",1.0F,0.8F);
        ObjectAnimator.ofPropertyValuesHolder(view,x,y,a).setDuration(1800).start();
    }


    public void animationset(View view) {
        Animator set =  AnimatorInflater.loadAnimator(this,R.animator.attribute_animation);
        set.setDuration(3000);
        set.setTarget(view);
        set.start();


    }
}
