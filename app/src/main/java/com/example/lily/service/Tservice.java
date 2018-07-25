package com.example.lily.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by ljq
 * on 2018/7/3.
 */

public class Tservice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private IBinder binder = new TBinder();


    class TBinder extends Binder {
        Tservice getService() {
            return Tservice.this;
        }


    }

    private Random util  = new Random();

    public int getNumber(){
        return util.nextInt(100);
    }


}
