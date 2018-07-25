package com.example.lily.animationpractice.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/5/30.
 */

public class ViewAnimationActivity extends AppCompatActivity {


    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewanimation);
        textView = findViewById(R.id.tv_view_animation);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.view_animation);
        textView.startAnimation(animation);

     /*   AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(300);
        textView.startAnimation(alphaAnimation);*/

    }
}
