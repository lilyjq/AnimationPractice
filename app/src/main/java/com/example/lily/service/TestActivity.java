package com.example.lily.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/7/5.
 */

public class TestActivity extends AppCompatActivity{

    ServiceConnection connection;
    Tservice tservice;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Tservice.TBinder binder = (Tservice.TBinder) service;
                tservice = binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
       Intent intent = new Intent(this,Tservice.class);
       bindService(intent,connection,BIND_AUTO_CREATE);
       Toast.makeText(this,tservice.getNumber()+"",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
