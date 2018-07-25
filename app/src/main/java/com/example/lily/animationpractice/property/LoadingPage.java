package com.example.lily.animationpractice.property;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/6/7.
 */

public class LoadingPage extends AppCompatActivity {



    View ll_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_loading);
        ll_content = findViewById(R.id.content);

        ll_content.post(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics m = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(m);
                float endValue = (float) Math.hypot(m.widthPixels,m.heightPixels);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Animator animator = ViewAnimationUtils.createCircularReveal(ll_content,m.widthPixels,m.heightPixels,0,endValue);
                    animator.setDuration(1000);
                    animator.start();
                }
            }
        });

    }
}
