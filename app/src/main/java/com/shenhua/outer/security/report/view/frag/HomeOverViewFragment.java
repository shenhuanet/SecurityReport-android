package com.shenhua.outer.security.report.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.EventDate;
import com.shenhua.outer.security.report.bean.OneDayChart;
import com.shenhua.outer.security.report.core.BusProvider;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.utils.UserUtils;
import com.shenhua.outer.security.report.view.widget.LineChartWrapper;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class HomeOverViewFragment extends Fragment {

    private View mRootView;
    @BindView(R.id.linechart)
    LineChart mLineChart;
    private ArrayList<Integer> mDatas = new ArrayList<>();
    private LineChartWrapper mLineChartWrapper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.get().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.frag_home_overview, container, false);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initLinechart();
        return mRootView;
    }

    private void initLinechart() {
        for (int i = 0; i < 24; i++) {
            mDatas.add(i, 0);
        }
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            entries.add(new Entry(i, mDatas.get(i)));
        }
        if (mLineChartWrapper == null) {
            mLineChartWrapper = new LineChartWrapper(getContext(), mLineChart);
            mLineChartWrapper.create(entries);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Subscribe
    public void onDateChanged(EventDate date) {
        RetrofitHelper.get().getRetrofit().create(IService.class)
                .getOneDayCount(date.toString(), UserUtils.get().getUserId(getContext()))
                .enqueue(new Callback<OneDayChart>() {
                    @Override
                    public void onResponse(Call<OneDayChart> call, Response<OneDayChart> response) {
                        List<Integer> data = response.body().getData();
                        if (data != null && data.size() > 0) {
                            mDatas.clear();
                            mDatas.addAll(data);
                            mLineChartWrapper.refresh(mDatas);
                        }
                    }

                    @Override
                    public void onFailure(Call<OneDayChart> call, Throwable t) {
                    }
                });
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

}
