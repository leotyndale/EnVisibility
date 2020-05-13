package com.imuxuan.test;

import android.app.Application;

import com.imuxuan.envisibility.EnVisibility;

/**
 * Created by Yunpeng Li on 2020/5/12.
 */
public class EnApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EnVisibility.init(this);
    }
}
