package com.example.lily.animationpractice.property;

import android.animation.TypeEvaluator;

/**
 * Created by ljq
 * on 2018/6/5.
 */

public class ColorEvaluator implements TypeEvaluator<String> {

    private int currentR = -1;
    private int currentG = -1;
    private int currentB = -1;
    @Override
    public String evaluate(float fraction, String startValue, String endValue) {

        int startRed = Integer.parseInt(startValue.substring(1,3),16);
        int startGreen = Integer.parseInt(startValue.substring(3,5),16);
        int startBlue = Integer.parseInt(startValue.substring(5,7),16);
        int endRed = Integer.parseInt(endValue.substring(1,3),16);
        int endGreen = Integer.parseInt(endValue.substring(3,5),16);
        int endBlue = Integer.parseInt(endValue.substring(5,7),16);

        if(currentR == -1){
            currentR = startRed;
        }
        if(currentG == -1){
            currentG = startGreen;
        }
        if(currentB == -1){
            currentB = startBlue;
        }

        int red_dif = Math.abs(startRed-endRed);
        int green_dif = Math.abs(startGreen-endGreen);
        int blue_dif = Math.abs(startBlue-endBlue);
        int color_diff = red_dif+green_dif+blue_dif;
        if(currentR != endRed){
          currentR = getCurrentColor(startRed,endRed,color_diff,0,fraction);
        }
        if(currentG != endGreen){
            currentG = getCurrentColor(startGreen,endGreen,color_diff,0,fraction);
        }
        if(currentB != endBlue){
            currentB = getCurrentColor(startBlue,endBlue,color_diff,0,fraction);
        }


        String currentColor = "#"+getHexString(currentR)
                + getHexString(currentG) + getHexString(currentB);
        return currentColor;
    }


    private int getCurrentColor(int startColor,int endColor,int color_diff,int offset,float fraction){

        int currentColor;
        if(startColor>endColor){
            currentColor = (int) (startColor-(fraction*color_diff-offset));
            if(currentColor<endColor){
                currentColor = endColor;
            }
        }else{
            currentColor = (int) (startColor+(fraction*color_diff-offset));
            if(currentColor >endColor){
                currentColor = endColor;
            }
        }
        return  currentColor;
    }

    private String getHexString (int value){
        String hexString = Integer.toHexString(value);
        if(hexString.length()==1){
            hexString = "0"+hexString;
        }
        return  hexString;
    }
}
