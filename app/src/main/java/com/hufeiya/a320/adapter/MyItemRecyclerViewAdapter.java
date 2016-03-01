package com.hufeiya.a320.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hufeiya.a320.R;
import com.hufeiya.a320.bean.Info;
import com.hufeiya.a320.fragment.ItemFragment.OnListFragmentInteractionListener;
import com.hufeiya.a320.dummy.DummyContent;
import com.hufeiya.a320.helper.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Info} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>
        implements ItemTouchHelperAdapter{

    private final List<Info> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Info> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mTypeView.setText(mValues.get(position).getType());
        holder.mSystemView.setText(mValues.get(position).getSystem());
        holder.mDateView.setText(mValues.get(position).getDate());
        holder.mSiteView.setText(mValues.get(position).getSite());
        holder.mATAView.setText(mValues.get(position).getATA());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onItemMove(int from, int to) {
        Collections.swap(mValues, from, to);
        notifyItemMoved(from, to);
    }

    @Override
    public void onItemDismiss(int position) {
        DummyContent.removeById(mValues.get(position).getId());
        mValues.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mTypeView;
        public final TextView mSystemView;
        public final TextView mDateView;
        public final TextView mSiteView;
        public final TextView mATAView;
        public Info mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id_info);
            mTypeView = (TextView) view.findViewById(R.id.type_info);
            mSystemView = (TextView) view.findViewById(R.id.system_info);
            mDateView = (TextView) view.findViewById(R.id.date_info);
            mSiteView = (TextView) view.findViewById(R.id.site_info);
            mATAView  = (TextView) view.findViewById(R.id.ATA_info);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
