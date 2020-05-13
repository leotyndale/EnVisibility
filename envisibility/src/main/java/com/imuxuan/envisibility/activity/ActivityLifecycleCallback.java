package com.imuxuan.envisibility.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.imuxuan.envisibility.observer.notifier.IVisibilityObserverNotifier;
import com.imuxuan.envisibility.observer.VisibilityObserver;
import com.imuxuan.envisibility.observer.notifier.VisibilityObserverNotifier;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yunpeng Li on 2020/5/12.
 */
public class ActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {

    private static HashSet<Integer> visibleActivities = new HashSet<>();
    private static ConcurrentHashMap<Integer, IVisibilityObserverNotifier> observerNotifiers = new ConcurrentHashMap<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }


    @Override
    public void onActivityResumed(Activity activity) {
        visibleActivities.add(activity.hashCode());
        notify(activity.hashCode(), true);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        visibleActivities.remove(activity.hashCode());
        notify(activity.hashCode(), false);
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private void notify(int activityHashCode, boolean isVisible) {
        IVisibilityObserverNotifier observerNotifier = observerNotifiers.get(activityHashCode);
        if (observerNotifier == null) {
            return;
        }
        observerNotifier.notify(isVisible);
    }

    public static HashSet<Integer> getVisibleActivities() {
        return visibleActivities;
    }

    public static void observe(int activityHashCode, VisibilityObserver observer) {
        if (observer == null) {
            return;
        }
        IVisibilityObserverNotifier observerNotifier = observerNotifiers.get(activityHashCode);
        if (observerNotifier == null) {
            observerNotifier = new VisibilityObserverNotifier();
            observerNotifiers.put(activityHashCode, observerNotifier);
        }
        observerNotifier.observe(observer);
    }

    public static void unObserve(int activityHashCode) {
        observerNotifiers.remove(activityHashCode);
    }

    public static void unObserve(int activityHashCode, VisibilityObserver observer) {
        if (observer == null) {
            return;
        }
        IVisibilityObserverNotifier observerNotifier = observerNotifiers.get(activityHashCode);
        if (observerNotifier == null) {
            return;
        }
        observerNotifier.unObserve(observer);
    }
}
