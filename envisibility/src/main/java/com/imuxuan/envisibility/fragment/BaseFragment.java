package com.imuxuan.envisibility.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.imuxuan.envisibility.VisibilityState;
import com.imuxuan.envisibility.VisibleElement;


/**
 * Created by Yunpeng Li on 2020/5/7.
 */
public class BaseFragment extends Fragment implements IFragmentLifecycle, VisibleElement {

    private IFragmentLifecycle fragmentLifecycle = new FragmentLifecycle(this);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentLifecycle.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        fragmentLifecycle.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        fragmentLifecycle.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        fragmentLifecycle.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public VisibilityState getState() {
        return ((VisibleElement) fragmentLifecycle).getState();
    }
}
