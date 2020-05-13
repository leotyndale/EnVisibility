package com.imuxuan.envisibility.observer.notifier;

import com.imuxuan.envisibility.observer.VisibilityObserver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Yunpeng Li on 2020/5/12.
 */
public class VisibilityObserverNotifier implements IVisibilityObserverNotifier {

    private List<VisibilityObserver> visibilityObserver;

    @Override
    public void observe(VisibilityObserver observer) {
        if (observer == null) {
            return;
        }
        if (visibilityObserver == null) {
            visibilityObserver = new CopyOnWriteArrayList<>();
        }
        visibilityObserver.add(observer);
    }

    @Override
    public void unObserve(VisibilityObserver observer) {
        if (observer == null || visibilityObserver == null || visibilityObserver.isEmpty()) {
            return;
        }
        visibilityObserver.remove(observer);
    }

    @Override
    public void notify(boolean isVisible) {
        if (visibilityObserver == null || visibilityObserver.isEmpty()) {
            return;
        }
        for (VisibilityObserver observer : visibilityObserver) {
            if (observer == null) {
                continue;
            }
            observer.onChange(isVisible);
        }
    }

    @Override
    public void clear() {
        if (visibilityObserver == null || visibilityObserver.isEmpty()) {
            return;
        }
        visibilityObserver.clear();
    }
}
