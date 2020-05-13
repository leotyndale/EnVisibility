package com.imuxuan.envisibility.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.imuxuan.envisibility.VisibilityState;
import com.imuxuan.envisibility.VisibleElement;

import java.util.Collections;
import java.util.List;

/**
 * Created by Yunpeng Li on 2020/5/11.
 */
public class FragmentUtils {

    public static boolean isFragmentVisible(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        boolean parentVisible = isParentVisible(fragment);
        boolean currentVisible = isSingleFragmentVisible(fragment);
        return parentVisible && currentVisible;
    }

    public static boolean isParentVisible(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        Fragment parent = fragment.getParentFragment();
        if (parent instanceof VisibleElement) {
            VisibilityState monitor = ((VisibleElement) parent).getState();
            return monitor.isVisible();
        } else if (parent != null) {
            return isSingleFragmentVisible(parent);
        } else {
            return true;
        }
    }

    public static boolean isSingleFragmentVisible(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        return fragment.isResumed() && !fragment.isHidden() && fragment.getUserVisibleHint();
    }

    public static List<Fragment> getChildFragments(Fragment fragment) {
        if (fragment == null) {
            return Collections.emptyList();
        }
        FragmentManager fragmentManager = fragment.getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.size() == 0) {
            return Collections.emptyList();
        }
        return fragments;
    }
}
