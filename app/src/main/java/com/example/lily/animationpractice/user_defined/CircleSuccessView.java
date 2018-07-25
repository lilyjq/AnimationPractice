package com.example.lily.animationpractice.user_defined;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/6/1.
 */

public class CircleSuccessView extends View{


   private int Colors = Color.GREEN;
   private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
   private RectF rectF = new RectF();
   private float stroke_width;



    public CircleSuccessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleSuccessView);
        Colors = array.getColor(R.styleable.CircleSuccessView_circle_color,Color.BLUE);
        stroke_width = array.getFloat(R.styleable.CircleSuccessView_stroke_width,5);
        array.recycle();
        init();
    }



    private void init(){
        paint.setColor(Colors);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(stroke_width);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int Width = getWidth();
        int Height = getHeight();
        int Radius = Math.min(Width,Height)/2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int PaddingTop = getPaddingTop();
        int paddingbottom =getPaddingBottom();
      //  canvas.drawCircle(Width/2,Height/2, Radius,paint);
        rectF.left = Width/2-Radius;
        rectF.right = Width/2+Radius;
        rectF.top = Height/2-Radius;
        rectF.bottom = Height/2+Radius;
        canvas.drawArc(rectF,0,360,true,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpaceMoede = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpaceMode == MeasureSpec.AT_MOST && heightSpaceMoede == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if (widthSpaceMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSpaceSize);
        }else if(heightSpaceMoede == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpaceSize,200);
        }
    }
}
