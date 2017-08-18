package com.yixun.pettyloan.event;

import com.yixun.pettyloan.ui.base.BaseSupportFragment;

/**
 * Created by zongkaili on 17-8-16.
 */

public class StartBrotherEvent {
    public BaseSupportFragment targetFragment;

    public StartBrotherEvent(BaseSupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
