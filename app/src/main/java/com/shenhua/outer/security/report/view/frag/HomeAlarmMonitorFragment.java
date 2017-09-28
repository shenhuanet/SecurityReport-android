package com.shenhua.outer.security.report.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.view.widget.NiceProgressBar;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        initViews();
    }

    private void initViews() {
        mCountProgress.setWheelColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mCountProgress.setStart(-60);
        mCountProgress.setTextMax(80);
        mCountProgress.show();

        mWarningProgress.setWheelColor(ContextCompat.getColor(getContext(), R.color.colorRed4));
        mWarningProgress.setStart(-90);
        mWarningProgress.setTextMax(50);
        mWarningProgress.show();

        mOnlineProgress.setWheelColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mOnlineProgress.setStart(20);
        mOnlineProgress.setTextMax(20);
        mOnlineProgress.show();
    }

}
