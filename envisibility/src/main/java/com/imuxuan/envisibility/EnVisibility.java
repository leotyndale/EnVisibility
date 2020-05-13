package com.imuxuan.envisibility;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.imuxuan.envisibility.activity.ActivityLifecycleCallback;
import com.imuxuan.envisibility.fragment.FragmentUtils;
import com.imuxuan.envisibility.observer.VisibilityObserver;

/**
 * Created by Yunpeng Li on 2020/5/11.
 */
public class EnVisibility {

    public static final String TAG = "Visibility";

    public static void init(Application context) {
        context.registerActivityLifecycleCallbacks(new ActivityLifecycleCallback());
    }

    public static boolean isVisible(VisibleElement element) {
        return element.getState().isVisible();
    }

    public static void observe(VisibleElement element, VisibilityObserver observer) {
        element.getState().observe(observer);
    }

    public static void unObserve(VisibleElement element) {
        element.getState().unObserve();
    }

    public static void unObserve(VisibleElement element, VisibilityObserver observer) {
        element.getState().unObserve(observer);
    }

    @Deprecated
    public static boolean isVisible(Fragment fragment) {
        return FragmentUtils.isFragmentVisible(fragment);
    }

    public static boolean isVisible(Activity activity) {
        return ActivityLifecycleCallback.getVisibleActivities().contains(activity.hashCode());
    }

    public static void observe(Activity activity, VisibilityObserver observer) {
        ActivityLifecycleCallback.observe(activity.hashCode(), observer);
    }

    public static void unObserve(Activity activity) {
        ActivityLifecycleCallback.unObserve(activity.hashCode());
    }

    public static void unObserve(Activity activity, VisibilityObserver observer) {
        ActivityLifecycleCallback.unObserve(activity.hashCode(), observer);
    }

    public static boolean isVisible(View view) {
        if (view == null) {
            return false;
        }
        return view.getVisibility() == View.VISIBLE;
    }

    public static boolean isVisible(Drawable drawable) {
        if (drawable == null) {
            return false;
        }
        return drawable.isVisible();
    }
}
