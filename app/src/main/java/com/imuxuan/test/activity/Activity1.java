package com.imuxuan.test.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.imuxuan.envisibility.EnVisibility;
import com.imuxuan.envisibility.observer.VisibilityObserver;
import com.imuxuan.test.R;
import com.imuxuan.test.fragment.Fragment1;
import com.imuxuan.test.fragment.Fragment2;

public class Activity1 extends AppCompatActivity {

    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();

    private ViewPager pager;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnVisibility.observe(this, new VisibilityObserver() {
            @Override
            public void onChange(boolean isVisible) {
                Log.i(EnVisibility.TAG, this + " isVisible:" + isVisible);
            }
        });

        pager = findViewById(R.id.pager);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragment1;
                    case 1:
                        return fragment2;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        pager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EnVisibility.unObserve(this);
    }

}
