package com.hufeiya.a320.helper;

/**
 * Created by hufeiya on 16-2-23.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
