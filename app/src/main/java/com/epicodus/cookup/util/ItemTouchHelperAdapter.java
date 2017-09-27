package com.epicodus.cookup.util;

/**
 * Created by Guest on 9/27/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
