package com.imuxuan.test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuxuan.envisibility.EnVisibility;
import com.imuxuan.envisibility.fragment.BaseFragment;
import com.imuxuan.envisibility.observer.VisibilityObserver;
import com.imuxuan.test.R;

/**
 * Created by Yunpeng Li on 2020/5/12.
 */
public class Fragment2 extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EnVisibility.observe(this, new VisibilityObserver() {
            @Override
            public void onChange(boolean isVisible) {
                Log.i(EnVisibility.TAG, this + " isVisible:" + isVisible);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EnVisibility.unObserve(this);
    }
}
