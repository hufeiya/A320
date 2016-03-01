package com.hufeiya.a320.application;

import android.app.Application;

import com.hufeiya.a320.R;

/**
 * Created by hufeiya on 16-2-24.
 */
public class CustomApplication extends Application
{
    private static String[] RAW;

    @Override
    public void onCreate()
    {
        super.onCreate();
        RAW = getResources().getStringArray(R.array.raw);

    }
    public static String[] getRAW() {
        return RAW;
    }

    public static void setRAW(String[] RAW) {
        CustomApplication.RAW = RAW;
    }

}