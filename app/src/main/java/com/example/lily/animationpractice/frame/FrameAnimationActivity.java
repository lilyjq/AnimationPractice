package com.example.lily.animationpractice.frame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/5/30.
 */

public class FrameAnimationActivity extends AppCompatActivity{



    private ImageView textView;
    private AnimationDrawable animation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frameanimation);
        textView = findViewById(R.id.iv_frame);
        textView.setBackgroundResource(R.drawable.animationlist);
        animation = (AnimationDrawable) textView.getBackground();
        animation.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(animation.isRunning()){
            animation.stop();
        }
        if(animation!=null){
            animation = null;
        }
    }
}
