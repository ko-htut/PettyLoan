package com.yixun.pettyloan.ui.widge;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zongkaili on 17-8-18.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public SpaceDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = mSpace;
        } else {
            outRect.top = 0;
        }
    }
}
