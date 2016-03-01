package com.hufeiya.a320.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.hufeiya.a320.fragment.PlaceholderFragment;
import com.hufeiya.a320.R;
import com.hufeiya.a320.dummy.DummyContent;
import com.hufeiya.a320.helper.MyViewPager;

public class DetailActivity extends AppCompatActivity implements PlaceholderFragment.InfoState {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private MyViewPager mViewPager;
    private Boolean isEditable = false;
    private Toolbar toolbar;
    private Boolean isSaved = true;
    private FloatingActionButton fabUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("故障详情");
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (MyViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(getIntent().getBundleExtra(getString(R.string.BUNDLE)).
                getInt(getString(R.string.index_in_query)));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ValueAnimator rotateAnimator = ObjectAnimator.ofFloat(view,"rotation",0,360).setDuration(500);
                Fragment page = getTheCurrentFragment();

                if( ! isEditable){
                    rotateAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            ((ImageView)view).setImageDrawable(getResources().getDrawable(R.drawable.ic_done_white_24dp));
                        }
                    });
                    rotateAnimator.start();
                    // based on the current position you can then cast the page to the correct
                    // class and call the method:
                    if (page != null) {
                        ((PlaceholderFragment)page).setEditable();

                    }
                    isEditable = true;
                    Snackbar.make(view, "进入可编辑状态", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    if( ! isSaved){
                        ((PlaceholderFragment)page).saveChanging();
                        isSaved = true;
                    }
                    rotateAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            ((ImageView)view).setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_edit));
                        }
                    });
                    ((PlaceholderFragment)page).setUnEditable();
                    isEditable = false;
                    toolbar.setSubtitle("");
                    fabUndo.setVisibility(View.INVISIBLE);
                    mViewPager.setNoScroll(false);
                    Snackbar.make(view, "更新成功!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

        fabUndo = (FloatingActionButton) findViewById(R.id.fab_undo);
        fabUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! isSaved){
                    isSaved = true;
                    isEditable = false;
                    ((ImageView)fab).setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_edit));
                    Fragment page = getTheCurrentFragment();
                    if (page != null) {
                        ((PlaceholderFragment)page).setText();
                        ((PlaceholderFragment)page).setUnEditable();
                    }
                    toolbar.setSubtitle("");
                    v.setVisibility(View.INVISIBLE);
                    mViewPager.setNoScroll(false);
                    Snackbar.make(v, "已取消更改", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }

    /**
     * It's a trick for getting the current fragment<br />
     * http://stackoverflow.com/questions/18609261/getting-the-current-fragment-instance-in-the-viewpager
     * */
    private Fragment getTheCurrentFragment(){
        return getSupportFragmentManager().findFragmentByTag("android:switcher:" +
                R.id.container + ":" + mViewPager.getCurrentItem());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return DummyContent.getCurrentQueryResult().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    @Override
    public void onInfoChanged() {
        toolbar.setSubtitle("(*未保存)");
        isSaved = false;
        mViewPager.setNoScroll(true);
        fabUndo.setVisibility(View.VISIBLE);
        //fabUndo.setBackgroundColor(getResources().getColor(R.color.white));
    }

}
