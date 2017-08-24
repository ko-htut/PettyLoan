package com.yixun.pettyloan.ui.widge;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zongkaili on 17-8-18.
 */

public class SpaceDecorationExceptFirst extends RecyclerView.ItemDecoration {
    private int mSpace;

    public SpaceDecorationExceptFirst(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildLayoutPosition(view) == 0)
            return;
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

        if (parent.getChildLayoutPosition(view) == 1) {
            outRect.top = mSpace;
        } else {
            outRect.top = 0;
        }
    }
}
