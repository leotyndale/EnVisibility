package com.imuxuan.envisibility;

import com.imuxuan.envisibility.observer.VisibilityObserver;

/**
 * Created by Yunpeng Li on 2020/5/11.
 */
public interface VisibilityState {

    boolean isVisible();

    void update(boolean visible);

    void observe(VisibilityObserver observer);

    void unObserve(VisibilityObserver observer);

    void unObserve();

}
