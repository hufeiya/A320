package com.hufeiya.a320.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.hufeiya.a320.R;
import com.hufeiya.a320.bean.Info;
import com.hufeiya.a320.dummy.DummyContent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddItemActivity extends AppCompatActivity {

    @Bind(R.id.id_info_add)EditText idText;
    @Bind(R.id.type_info_add)EditText typeText;
    @Bind(R.id.system_info_add)EditText systemText;
    @Bind(R.id.date_info_add)EditText dateText;
    @Bind(R.id.site_info_add)EditText siteText;
    @Bind(R.id.ATA_info_add)EditText ATAText;
    @Bind(R.id.jobType_info_add)EditText jobTypeText;
    @Bind(R.id.discryption_info_add)EditText discryptionText;
    @Bind(R.id.measure_info_add)EditText measureText;
    @Bind(R.id.unitNo_info_add)EditText unitNoText;
    @Bind(R.id.sequenceNo_info_add)EditText sequenceNoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加条目");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Info currentInfo = new Info();
                currentInfo.setId(DummyContent.ITEMS.size());
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
                DummyContent.ITEMS.add(currentInfo);
                Snackbar.make(view, "已添加", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        idText.setText("" + DummyContent.ITEMS.size());
        idText.setFocusable(false);
        idText.setFocusableInTouchMode(false);
    }

}
