package com.shenhua.outer.security.report.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.FragmentAdapter;
import com.shenhua.outer.security.report.view.frag.HomeFragment;
import com.shenhua.outer.security.report.view.frag.MeFragment;
import com.shenhua.outer.security.report.view.frag.MonitorFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    private Unbinder mUnBinder;
    private static Boolean isExit = false;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mViewPager.setOffscreenPageLimit(3);
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
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
                return false;
            }
            Timer tExit;
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            } else {
                this.finish();
                System.exit(0);
            }
        }
        return false;
    }
}
