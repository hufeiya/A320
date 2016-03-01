package com.hufeiya.a320.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hufeiya.a320.bean.Info;
import com.hufeiya.a320.adapter.MyItemRecyclerViewAdapter;
import com.hufeiya.a320.R;
import com.hufeiya.a320.dummy.DummyContent;
import com.hufeiya.a320.helper.OnStartDragListener;
import com.hufeiya.a320.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment implements OnStartDragListener{


    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String SYSTEM = "system";
    private static final String ATA = "ATA";

    private int mColumnCount = 1;
    private String mSystem;
    private String mATA;
    private OnListFragmentInteractionListener mListener;
    private ItemTouchHelper touchHelper;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount,String system,String ata) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(SYSTEM,system);
        args.putString(ATA,ata);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mSystem = getArguments().getString(SYSTEM);
            mATA = getArguments().getString(ATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            DummyContent.setCurrentQueryResult(query());
            MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(DummyContent.getCurrentQueryResult(),mListener);
            recyclerView.setAdapter(adapter);
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
            touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);

        }
        return view;
    }

    private List<Info> query() {
        List<Info> queryResult = new ArrayList<>();
        if(mSystem.isEmpty() && mATA.isEmpty()){
            return DummyContent.ITEMS;
        }else if(mSystem.isEmpty()){
            for(Info info : DummyContent.ITEMS){
                if(info.getATA().contains(mATA)){
                    queryResult.add(info);
                }
            }
        }else if(mATA.isEmpty()){
            for(Info info : DummyContent.ITEMS){
                if(info.getSystem().contains(mSystem)){
                    queryResult.add(info);
                }
            }
        }else{
            for(Info info : DummyContent.ITEMS){
                if(info.getATA().contains(mATA) && info.getSystem().contains(mSystem)){
                    queryResult.add(info);
                }
            }
        }
        return queryResult;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Info item);
    }
}
