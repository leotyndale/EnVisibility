package com.imuxuan.envisibility.fragment;

import android.support.v4.app.Fragment;

import com.imuxuan.envisibility.VisibilityState;
import com.imuxuan.envisibility.VisibleElement;

/**
 * Created by Yunpeng Li on 2020/5/11.
 */
public class FragmentLifecycle implements IFragmentLifecycle, VisibleElement {

    private VisibilityState visibilityState;

    public FragmentLifecycle(Fragment fragment) {
        visibilityState = new FragmentVisibilityState(fragment);
    }

    @Override
    public void onResume() {
        visibilityState.update(true);
    }

    @Override
    public void onPause() {
        visibilityState.update(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        visibilityState.update(!hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        visibilityState.update(isVisibleToUser);
    }

    @Override
    public VisibilityState getState() {
        return visibilityState;
    }
}
