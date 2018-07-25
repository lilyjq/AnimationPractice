package com.example.lily.animationpractice.property;

import android.animation.Keyframe;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;

/**
 * Created by ljq
 * on 2018/7/12.
 */

public class PathView extends View {


    KeyFrames keyframe;
    Paint mpaint;
    float[] light_point;
    float[] dark_point;
    int light_color;
    int dark_color;
    ValueAnimator mProgressAnimator;
    Path  path;


    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint.setStyle(Paint.Style.STROKE);
        light_color = Color.RED;
        dark_color = Color.DKGRAY;
        path = new Path();
        path.moveTo(200,0);
        path.lineTo(200,700);
        setPath(path);

    }


    public void setDarkColor(@ColorInt int color) {
        dark_color = color;
    }

    public void setlightColor(@ColorInt int color) {
        light_color = color;
    }

    public void setPath(Path path) {
        keyframe = new KeyFrames(path);
    }

    public void setStrokeWidth(float width) {
        mpaint.setStrokeWidth(width);
    }

    public void setLightProgress(float start, float end) {
        setLineProgress(start, end, true);

    }

    public void setDarkProgress(float start, float end) {
        setLineProgress(start, end, false);


    }
    private void startUpdateProgress(){
        mProgressAnimator = ValueAnimator.ofFloat(-.6F,1).setDuration(2000);
        mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                float lightLineStartProgress,lightLineEndProgress;
                float darkLineStartProgress,darkLineEndProgress;
                darkLineEndProgress = currentProgress;


                darkLineStartProgress = lightLineStartProgress = (.6F +currentProgress)*2;
                lightLineEndProgress = .35F+ currentProgress;

                if(lightLineEndProgress>.3F){
                    lightLineEndProgress = (.35F+currentProgress-.3F)*2+.3F;
                }
                if(darkLineStartProgress > .65F){
                    darkLineStartProgress = lightLineStartProgress = ((.6F+currentProgress)*2-.65F)*.35F+.65F;

                }
                if(lightLineEndProgress <0){
                    lightLineEndProgress = 0;
                }
                if(darkLineEndProgress<0){
                    darkLineEndProgress = 0;
                }

                if(lightLineStartProgress >1){
                    darkLineEndProgress = lightLineStartProgress = 1;
                }
                setLightProgress(lightLineStartProgress,lightLineEndProgress);
                setDarkProgress(darkLineStartProgress,darkLineEndProgress);
            }
        });
        mProgressAnimator.start();
    }


    private void setLineProgress(float start, float end, boolean isLightPoint) {

        if (keyframe == null)
            throw new IllegalStateException("path not set yet");
        if (isLightPoint) {
            light_point = keyframe.getRangeValue(start, end);
        } else {
            dark_point = keyframe.getRangeValue(start, end);

        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mpaint.setColor(dark_color);
        if (dark_point != null) canvas.drawPoints(dark_point, mpaint);
        mpaint.setColor(light_color);
        if (light_point != null) canvas.drawPoints(light_point, mpaint);
        startUpdateProgress();
    }

    private static class KeyFrames {

        static final float PRECENT = 1f;
        int numPoints;
        float[] mData;

        KeyFrames(Path path) {
            init(path);

        }

        void init(Path path) {

            PathMeasure pathMeasure = new PathMeasure(path, false);
            float pathLength = pathMeasure.getLength();
            numPoints = (int) ((pathLength / PRECENT) + 1);
            mData = new float[numPoints * 2];
            float[] position = new float[2];
            int index = 0;
            for (int i = 0; i < numPoints; ++i) {

                float distance = (i * pathLength) / (numPoints - 1);
                pathMeasure.getPosTan(distance, position, null);
                mData[index] = position[0];
                mData[index + 1] = position[1];
                index += 2;

            }
            numPoints = mData.length;
        }


        float[] getRangeValue(float start, float end) {
            if (start >= end) {
                return null;
            }
            int startIndex = (int) (numPoints * start);
            int endIndex = (int) (numPoints * end);
            if (startIndex % 2 != 0) {
                --startIndex;
            }
            if (endIndex % 2 != 0) {
                ++endIndex;
            }
            return Arrays.copyOfRange(mData, startIndex, endIndex);
        }

    }

}
