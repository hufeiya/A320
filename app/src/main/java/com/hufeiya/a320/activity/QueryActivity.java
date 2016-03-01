package com.hufeiya.a320.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.hufeiya.a320.bean.Info;
import com.hufeiya.a320.fragment.ItemFragment;
import com.hufeiya.a320.R;
import com.hufeiya.a320.dummy.DummyContent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QueryActivity extends AppCompatActivity
        implements ItemFragment.OnListFragmentInteractionListener {

    @Bind(R.id.query_button)Button queryButton;
    @Bind(R.id.nested_form)NestedScrollView nestedScrollView;
    @Bind(R.id.system_query_view)AutoCompleteTextView systemText;
    @Bind(R.id.ATA_query_view)AutoCompleteTextView aTAText;
    private ItemFragment itemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("查询或添加");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueryActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.setVisibility(View.INVISIBLE);
                itemFragment = ItemFragment.newInstance(1,systemText.getText().toString(),aTAText.getText().toString());
                getFragmentManager().beginTransaction().replace(R.id.fragment_replace,itemFragment).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(itemFragment != null){
            getFragmentManager().beginTransaction().remove(itemFragment).commit();
            itemFragment = null;
            nestedScrollView.setVisibility(View.VISIBLE);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public void onListFragmentInteraction(Info item) {
        Bundle bundle = new Bundle();
        int itemIndex = DummyContent.findIndexByIdInCurrentQueryResult(item.getId());
        bundle.putInt(getString(R.string.index_in_query),itemIndex);
        Intent intent = new Intent(QueryActivity.this,DetailActivity.class);
        intent.putExtra(getString(R.string.BUNDLE),bundle);
        startActivity(intent);
    }

}
