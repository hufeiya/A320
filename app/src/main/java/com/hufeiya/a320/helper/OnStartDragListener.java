package com.hufeiya.a320.helper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by hufeiya on 16-2-23.
 */
public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
