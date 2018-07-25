package com.example.lily.animationpractice.property;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ljq
 * on 2018/6/29.
 */

public class WaveView extends View {

    private Paint circle_paint;
    private Paint text_paint;
    private Paint wave_paint;
    private Point startPoint;
    private int width;
    private int height;
    private int cycler =150;
    private Path path;
    private Path circle_path;
    private int progress ;

    private Context context;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        circle_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circle_paint.setColor(Color.GRAY);
        circle_paint.setStrokeWidth(20);
        circle_paint.setStyle(Paint.Style.STROKE);


        text_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        text_paint.setColor(Color.BLUE);
        text_paint.setStyle(Paint.Style.STROKE);
        text_paint.setTextSize(dip2px(context, 20));

        wave_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wave_paint.setStyle(Paint.Style.FILL);
        wave_paint.setColor(Color.GREEN);
        wave_paint.setStrokeWidth(dip2px(context, 5));
        path = new Path();
        circle_path = new Path();



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawView(canvas);
    }

    private void drawView(Canvas canvas) {
        int raduis = Math.min(width / 2,height/2);
        int height_real = raduis*2;
        int heights = height/2+raduis;

        circle_path.addCircle(width / 2, height / 2, raduis, Path.Direction.CW);
        canvas.clipPath(circle_path);
        canvas.drawPaint(circle_paint);
        canvas.drawCircle(width / 2, height / 2, raduis, circle_paint);
        startPoint.y = (int) (heights - (progress / 100.0 * height_real));
        path.moveTo(startPoint.x, startPoint.y);

       /* for (int i = 1; i < 8; i++) {

            if (i % 2 == 0) {
                path.quadTo(startPoint.x + cycler * i * 2, startPoint.y-80, startPoint.x + cycler * 4 * i, startPoint.y);
            } else {
                path.quadTo(startPoint.x + cycler * i * 6, startPoint.y+80, startPoint.x + cycler * 8 * i, startPoint.y);
            }

        }*/



        int j = 1;
        //循环绘制正弦曲线 循环一次半个周期
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {//2,4,6,8  j = 3,7,11,15
                //波谷
                path.quadTo(startPoint.x + (cycler * j), startPoint.y + 80,
                        startPoint.x + (cycler * 2) * i, startPoint.y);
            } else {//1,3,5,7  j = 1,5,9,13
                //波峰
                path.quadTo(startPoint.x + (cycler * j), startPoint.y - 80,
                        startPoint.x + (cycler * 2) * i, startPoint.y);
            }
            j += 2;
        }

        path.lineTo(width, height);
        path.lineTo(startPoint.x, height);
        path.lineTo(startPoint.x, startPoint.y);
        path.close();
        canvas.drawPath(path, wave_paint);

        if (startPoint.x + 40 >= 0) {
            //满了一个周期则恢复默认起点继续平移
            startPoint.x = -cycler * 4;
        }
        drawText(canvas,text_paint,progress + "%");
        startPoint.x = startPoint.x + 40;

        if (progress>=100) {
            progress =0;
        }else{
            progress++;
        }
        path.reset();
        postInvalidateDelayed(200);


    }

    private void drawText(Canvas canvas,Paint paint,String text){
        Rect rect = new Rect(0,0,width,height);
        Paint.FontMetricsInt size= paint.getFontMetricsInt();
        int baseline = (rect.bottom + rect.top - size.bottom - size.top) / 2;
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, rect.centerX(), baseline, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getViewSize(400, widthMeasureSpec);
        height = getViewSize(400, heightMeasureSpec);
        startPoint = new Point(-cycler, height/2);
    }

    private int getViewSize(int w, int widthMeasureSpec) {
        int defaultSize = w;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            defaultSize = size;

        } else if (mode == MeasureSpec.EXACTLY) {
            defaultSize = size;

        } else if (mode == MeasureSpec.UNSPECIFIED) {
            defaultSize = w;

        }
        return defaultSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }


    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
