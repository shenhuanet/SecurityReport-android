package com.shenhua.outer.security.report.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.EventWarningDetect;
import com.shenhua.outer.security.report.core.BusProvider;
import com.shenhua.outer.security.report.view.widget.NiceProgressBar;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class HomeAlarmMonitorFragment extends Fragment {

    private View mRootView;
    @BindView(R.id.progressCount)
    NiceProgressBar mCountProgress;
    @BindView(R.id.progressWarning)
    NiceProgressBar mWarningProgress;
    @BindView(R.id.progressOnline)
    NiceProgressBar mOnlineProgress;
    @BindView(R.id.tvDectetAll)
    TextView mAllDectetTv;
    @BindView(R.id.tvDectetUnusual)
    TextView mUnusualDectetTv;
    @BindView(R.id.tvDectetOnline)
    TextView mOnlineDectetTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.get().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.frag_home_alarm_monitor, container, false);
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
//        initViews(80, 50, 20);
    }

    private void initViews(int all, int unusual, int online) {
        mCountProgress.setWheelColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mCountProgress.setStart(-90);
        mCountProgress.setTextMax(all);
        mCountProgress.show();
        mAllDectetTv.setText(String.valueOf(all));

        mWarningProgress.setWheelColor(ContextCompat.getColor(getContext(), R.color.colorRed4));
        mWarningProgress.setStart(-90);
        mWarningProgress.setTextMax(unusual);
        mWarningProgress.show();
        mUnusualDectetTv.setText(String.valueOf(unusual));

        mOnlineProgress.setWheelColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mOnlineProgress.setStart(-90);
        mOnlineProgress.setTextMax(online);
        mOnlineProgress.show();
        mOnlineDectetTv.setText(String.valueOf(online));
    }

    @Subscribe
    public void setProgressData(EventWarningDetect event) {
        initViews(event.getAll(), event.getUnusual(), event.getOnline());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.get().unregister(this);
    }
}
