package com.example.lily.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by ljq
 * on 2018/7/5.
 */

public class XService  extends Service{




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private Messenger messenger = new Messenger(new XHandler());


    class XHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  1:
                    Messenger repMessenger = msg.replyTo;
                    Message message = Message.obtain(null,1);
                    Bundle bundle = new Bundle();
                    bundle.putInt("number",getNumber());
                    message.setData(bundle);
                    try {
                        repMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    break;
                default:
                    break;


            }
        }
    }
    private Random util  = new Random();

    public int getNumber(){
        return util.nextInt(100);
    }

}
