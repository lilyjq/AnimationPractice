package com.example.lily.animationpractice.frame;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ljq
 * on 2018/7/9.
 */

public class TextDate  implements Parcelable{

    String t1;
    String t2;
    String  t3;

    protected TextDate(Parcel in) {
        t1 = in.readString();
        t2 = in.readString();
        t3 = in.readString();
    }

    public static final Creator<TextDate> CREATOR = new Creator<TextDate>() {
        @Override
        public TextDate createFromParcel(Parcel in) {
            return new TextDate(in);
        }

        @Override
        public TextDate[] newArray(int size) {
            return new TextDate[size];
        }
    };

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(t1);
        dest.writeString(t2);
        dest.writeString(t3);
    }
}
