package com.shenhua.outer.security.report.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.view.frag.WarningListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class WarningActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_monitor);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        getSupportFragmentManager().beginTransaction().add(R.id.frameMonitorList,
                WarningListFragment.newInstance()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
