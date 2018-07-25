package com.example.lily.animationpractice.property;

import android.animation.TypeEvaluator;

/**
 * Created by ljq
 * on 2018/6/5.
 */

public class MyPointEvaluator implements TypeEvaluator<MyPoint> {
    @Override
    public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {

        float x = startValue.getX()+fraction*(endValue.getX()-startValue.getX());
        float y = startValue.getY()+fraction*(endValue.getY()-startValue.getY());
         MyPoint point = new MyPoint(x,y);
        return point;
    }
}
