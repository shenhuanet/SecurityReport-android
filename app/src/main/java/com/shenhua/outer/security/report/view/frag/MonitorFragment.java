package com.shenhua.outer.security.report.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.EventFragmentNav;
import com.shenhua.outer.security.report.core.BusProvider;
import com.shenhua.outer.security.report.core.utils.Contanst;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 监测点页面
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class MonitorFragment extends Fragment {

    public static MonitorFragment newInstance() {
        return new MonitorFragment();
    }

    private static final String[] TITLES = {"站点", "网关", "监测点"};
    private View mRootView;
    private boolean isLoaded;
    private MonitorListFragment mMonitorListFragment;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tvTitle)
    TextView mTitileTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.get().register(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.frag_monitor, container, false);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resetToolbar();
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
    }

    /**
     * 重置toolbar显示
     */
    private void resetToolbar() {
        mToolbar.setNavigationIcon(null);
        mTitileTv.setText(TITLES[0]);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            initView();
        }
    }

    /**
     * 懒加载view
     */
    private void initView() {
        if (!isLoaded) {
            mMonitorListFragment = MonitorListFragment.newInstance(Contanst.PAGE_STATIONS, 0);
            getFragmentManager().beginTransaction().add(R.id.frameMonitorList, mMonitorListFragment)
                    .commit();
            isLoaded = true;
        }
        getFragmentManager().addOnBackStackChangedListener(() -> {
            int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
            if (backStackEntryCount == 0) {
                resetToolbar();
                return;
            }
            mToolbar.setNavigationIcon(R.drawable.ic_back);
            try {
                mTitileTv.setText(TITLES[backStackEntryCount]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void navPage(EventFragmentNav event) {
        mMonitorListFragment = MonitorListFragment.newInstance(event.getPageId(), event.getDataId());
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.anim_slide_right_in, R.anim.anim_slide_right_out)
                .replace(R.id.frameMonitorList, mMonitorListFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.get().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

}
