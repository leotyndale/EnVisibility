package com.imuxuan.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.imuxuan.envisibility.EnVisibility;
import com.imuxuan.envisibility.observer.VisibilityObserver;
import com.imuxuan.test.R;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EnVisibility.observe(this, new VisibilityObserver() {
            @Override
            public void onChange(boolean isVisible) {
                Log.i(EnVisibility.TAG, this + " isVisible:" + isVisible);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EnVisibility.unObserve(this);
    }
}
