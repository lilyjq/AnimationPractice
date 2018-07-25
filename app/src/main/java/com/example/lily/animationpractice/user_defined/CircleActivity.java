package com.example.lily.animationpractice.user_defined;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/6/1.
 */

public class CircleActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
       // Handler handler = new Handler();
       // handler.post()
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


            }
        };
    }
}
