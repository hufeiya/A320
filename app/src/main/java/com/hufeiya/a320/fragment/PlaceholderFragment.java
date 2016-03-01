package com.hufeiya.a320.fragment;

/**
 * Created by hufeiya on 16-2-26.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hufeiya.a320.bean.Info;
import com.hufeiya.a320.R;
import com.hufeiya.a320.dummy.DummyContent;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    @Bind(R.id.id_info)EditText idText;
    @Bind(R.id.type_info)EditText typeText;
    @Bind(R.id.system_info)EditText systemText;
    @Bind(R.id.date_info)EditText dateText;
    @Bind(R.id.site_info)EditText siteText;
    @Bind(R.id.ATA_info)EditText ATAText;
    @Bind(R.id.jobType_info)EditText jobTypeText;
    @Bind(R.id.discryption_info)EditText discryptionText;
    @Bind(R.id.measure_info)EditText measureText;
    @Bind(R.id.unitNo_info)EditText unitNoText;
    @Bind(R.id.sequenceNo_info)EditText sequenceNoText;
    private TextWatcher textWatcher;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,rootView);
        setText();
        return rootView;
    }

    public void setText(){
        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        List<Info> infos = DummyContent.getCurrentQueryResult();
        idText.setText(String.valueOf(infos.get(index).getId()));
        typeText.setText(infos.get(index).getType());
        systemText.setText(infos.get(index).getSystem());
        dateText.setText(infos.get(index).getDate());
        siteText.setText(infos.get(index).getSite());
        ATAText.setText(infos.get(index).getATA());
        jobTypeText.setText(infos.get(index).getJobType());
        discryptionText.setText(infos.get(index).getDiscryption());
        measureText.setText(infos.get(index).getMeasure());
        unitNoText.setText(infos.get(index).getUnitNo());
        sequenceNoText.setText(infos.get(index).getSequenceNo());


        discryptionText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        discryptionText.setGravity(Gravity.TOP);
        discryptionText.setSingleLine(false);
        discryptionText.setHorizontallyScrolling(false);

        measureText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        measureText.setGravity(Gravity.TOP);
        measureText.setSingleLine(false);
        measureText.setHorizontallyScrolling(false);
    }

    public void setEditable(){
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(getActivity() instanceof InfoState){
                    ((InfoState) getActivity()).onInfoChanged();
                }
            }
        };
        idText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"id不可更改",Toast.LENGTH_SHORT).show();
            }
        });
        typeText.setFocusableInTouchMode(true);
        systemText.setFocusableInTouchMode(true);
        dateText.setFocusableInTouchMode(true);
        siteText.setFocusableInTouchMode(true);
        ATAText.setFocusableInTouchMode(true);
        jobTypeText.setFocusableInTouchMode(true);
        discryptionText.setFocusableInTouchMode(true);
        measureText.setFocusableInTouchMode(true);
        unitNoText.setFocusableInTouchMode(true);
        sequenceNoText.setFocusableInTouchMode(true);

        typeText.addTextChangedListener(textWatcher);
        systemText.addTextChangedListener(textWatcher);
        dateText.addTextChangedListener(textWatcher);
        siteText.addTextChangedListener(textWatcher);
        ATAText.addTextChangedListener(textWatcher);
        jobTypeText.addTextChangedListener(textWatcher);
        discryptionText.addTextChangedListener(textWatcher);
        measureText.addTextChangedListener(textWatcher);
        unitNoText.addTextChangedListener(textWatcher);
        sequenceNoText.addTextChangedListener(textWatcher);
    }

    public void setUnEditable(){
        idText.setFocusable(false);
        typeText.setFocusable(false);
        systemText.setFocusable(false);
        dateText.setFocusable(false);
        siteText.setFocusable(false);
        ATAText.setFocusable(false);
        jobTypeText.setFocusable(false);
        discryptionText.setFocusable(false);
        measureText.setFocusable(false);
        unitNoText.setFocusable(false);
        sequenceNoText.setFocusable(false);

        idText.setFocusableInTouchMode(false);
        typeText.setFocusableInTouchMode(false);
        systemText.setFocusableInTouchMode(false);
        dateText.setFocusableInTouchMode(false);
        siteText.setFocusableInTouchMode(false);
        ATAText.setFocusableInTouchMode(false);
        jobTypeText.setFocusableInTouchMode(false);
        discryptionText.setFocusableInTouchMode(false);
        measureText.setFocusableInTouchMode(false);
        unitNoText.setFocusableInTouchMode(false);
        sequenceNoText.setFocusableInTouchMode(false);
    }

    public void saveChanging(){
        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        List<Info> infos = DummyContent.getCurrentQueryResult();
        Info currentInfo = infos.get(index);
        currentInfo.setType(typeText.getText().toString());
        currentInfo.setSystem(systemText.getText().toString());
        currentInfo.setDate(dateText.getText().toString());
        currentInfo.setSite(siteText.getText().toString());
        currentInfo.setATA(ATAText.getText().toString());
        currentInfo.setJobType(jobTypeText.getText().toString());
        currentInfo.setDiscryption(discryptionText.getText().toString());
        currentInfo.setMeasure(measureText.getText().toString());
        currentInfo.setUnitNo(unitNoText.getText().toString());
        currentInfo.setSequenceNo(sequenceNoText.getText().toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Context context = getActivity();
        if(context instanceof CurrentFragmentChangedListener){
            ((CurrentFragmentChangedListener) context).onFragmentChanged(this);
        }*/
        Log.d("fuckass","onCreate   " + getArguments().getInt(ARG_SECTION_NUMBER));
    }

    /**
     * Notify the info is changed.
     * */
    public interface InfoState{

        void onInfoChanged();
    }
}
