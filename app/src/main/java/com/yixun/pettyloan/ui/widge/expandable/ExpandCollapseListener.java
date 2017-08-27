package com.yixun.pettyloan.ui.widge.expandable;

import android.view.View;

public interface ExpandCollapseListener<P> {

    interface ExpandListener<P> {
        void onExpanded(int parentIndex, P parent, View view);
    }

    interface CollapseListener<P> {
        void onCollapsed(int parentIndex, P parent, View view);
    }
}
