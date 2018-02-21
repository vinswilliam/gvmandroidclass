package com.gvm.todolist.list;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gvm.todolist.R;


public class ItemDecorView extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int margin = view.getContext().getResources().getDimensionPixelOffset(R.dimen.medium_margin);
        outRect.left = margin;
        outRect.right = margin;
        outRect.bottom = margin * 2;

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin * 2;
        }
    }
}
