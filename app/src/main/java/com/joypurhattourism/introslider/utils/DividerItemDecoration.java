package com.joypurhattourism.introslider.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

public class DividerItemDecoration extends ItemDecoration {
    private static final int[] ATTRS;
    public static final int HORIZONTAL_LIST = 0;
    public static final int VERTICAL_LIST = 1;
    private Drawable mDivider;
    private int mOrientation;

    static {
        int[] iArr = new int[VERTICAL_LIST];
        iArr[HORIZONTAL_LIST] = 16843284;
        ATTRS = iArr;
    }

    public DividerItemDecoration(Context context, int orientation) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDivider = a.getDrawable(HORIZONTAL_LIST);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation == 0 || orientation == VERTICAL_LIST) {
            this.mOrientation = orientation;
            return;
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public void onDraw(Canvas c, RecyclerView parent) {
        if (this.mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = HORIZONTAL_LIST; i < childCount; i += VERTICAL_LIST) {
            View child = parent.getChildAt(i);
            int top = child.getBottom() + ((LayoutParams) child.getLayoutParams()).bottomMargin;
            this.mDivider.setBounds(left, top, right, top + this.mDivider.getIntrinsicHeight());
            this.mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = HORIZONTAL_LIST; i < childCount; i += VERTICAL_LIST) {
            View child = parent.getChildAt(i);
            int left = child.getRight() + ((LayoutParams) child.getLayoutParams()).rightMargin;
            this.mDivider.setBounds(left, top, left + this.mDivider.getIntrinsicHeight(), bottom);
            this.mDivider.draw(c);
        }
    }

    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (this.mOrientation == VERTICAL_LIST) {
            outRect.set(HORIZONTAL_LIST, HORIZONTAL_LIST, HORIZONTAL_LIST, this.mDivider.getIntrinsicHeight());
        } else {
            outRect.set(HORIZONTAL_LIST, HORIZONTAL_LIST, this.mDivider.getIntrinsicWidth(), HORIZONTAL_LIST);
        }
    }
}
