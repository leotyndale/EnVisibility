package com.imuxuan.envisibility.fragment;

import android.support.v4.app.Fragment;

import com.imuxuan.envisibility.observer.notifier.IVisibilityObserverNotifier;
import com.imuxuan.envisibility.observer.VisibilityObserver;
import com.imuxuan.envisibility.observer.notifier.VisibilityObserverNotifier;
import com.imuxuan.envisibility.VisibilityState;
import com.imuxuan.envisibility.VisibleElement;

import java.util.List;

public class FragmentVisibilityState implements VisibilityState {

    private Fragment fragment;
    private boolean isVisible;
    private IVisibilityObserverNotifier observerNotifier = new VisibilityObserverNotifier();

    public FragmentVisibilityState(Fragment mFragment) {
        this.fragment = mFragment;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void update(boolean visible) {
        if (fragment == null || visible == isVisible) {
            return;
        }
        boolean isVisible = FragmentUtils.isFragmentVisible(fragment);
        if (isVisible != this.isVisible) {
            onVisibleChange(isVisible);
        }
    }

    private void onVisibleChange(boolean fragmentVisible) {
        isVisible = fragmentVisible;
        observerNotifier.notify(isVisible);
        updateChildVisibility(isVisible);
    }

    private void updateChildVisibility(boolean expect) {
        if (fragment == null) {
            return;
        }
        updateChildVisibility(expect, FragmentUtils.getChildFragments(fragment));
    }

    private void updateChildVisibility(boolean expect, List<Fragment> fragments) {
        for (Fragment fragment : fragments) {
            if (!(fragment instanceof VisibleElement)) {
                continue;
            }
            VisibilityState visibilityState = ((VisibleElement) fragment).getState();
            visibilityState.update(expect);
        }
    }

    @Override
    public void observe(VisibilityObserver observer) {
        observerNotifier.observe(observer);
    }

    @Override
    public void unObserve(VisibilityObserver observer) {
        observerNotifier.unObserve(observer);
    }

    @Override
    public void unObserve() {
        observerNotifier.clear();
    }

}
