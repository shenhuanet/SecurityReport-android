package com.shenhua.outer.security.report.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.FragmentAdapter;
import com.shenhua.outer.security.report.view.frag.HomeFragment;
import com.shenhua.outer.security.report.view.frag.MeFragment;
import com.shenhua.outer.security.report.view.frag.MonitorFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder mUnBinder;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        mUnBinder = ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(MonitorFragment.newInstance());
        fragments.add(MeFragment.newInstance());
        final String[] titles = getResources().getStringArray(R.array.module_titles);
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        final int[] images = {R.drawable.ic_home_selector, R.drawable.ic_monitor_selector, R.drawable.ic_me_selector};

        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab item = mTabLayout.getTabAt(i);
            if (item != null) {
                item.setCustomView(R.layout.tablayout_tiem);
                TextView title = (TextView) item.getCustomView();
                if (title != null) {
                    title.setCompoundDrawablesWithIntrinsicBounds(0, images[i], 0, 0);
                    title.setText(titles[i]);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("shenhuaLog -- " + MainActivity.class.getSimpleName(), "onResume: ");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("shenhuaLog -- " + MainActivity.class.getSimpleName(), "onWindowFocusChanged: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
