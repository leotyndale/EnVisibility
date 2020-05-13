package com.imuxuan.envisibility.observer.notifier;

import com.imuxuan.envisibility.observer.VisibilityObserver;

/**
 * Created by Yunpeng Li on 2020/5/12.
 */
public interface IVisibilityObserverNotifier {

    void observe(VisibilityObserver observer);

    void unObserve(VisibilityObserver observer);

    void notify(boolean isVisible);

    void clear();
}
