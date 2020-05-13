package com.imuxuan.envisibility.fragment;

/**
 * Created by Yunpeng Li on 2020/5/11.
 */
public interface IFragmentLifecycle {

    void onResume();

    void onPause();

    void onHiddenChanged(boolean hidden);

    void setUserVisibleHint(boolean isVisibleToUser);
}
