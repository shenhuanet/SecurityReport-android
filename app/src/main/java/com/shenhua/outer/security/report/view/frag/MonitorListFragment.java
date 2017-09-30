package com.shenhua.outer.security.report.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.BaseItemDecoration;
import com.shenhua.outer.security.report.adapter.StationsAdapter;
import com.shenhua.outer.security.report.bean.EventFragmentNav;
import com.shenhua.outer.security.report.bean.UserStations;
import com.shenhua.outer.security.report.core.BusProvider;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.utils.AndroidUtils;
import com.shenhua.outer.security.report.core.utils.Contanst;
import com.shenhua.outer.security.report.core.utils.UserUtils;
import com.shenhua.outer.security.report.view.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 监测点页面
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class MonitorListFragment extends Fragment {

    public static MonitorListFragment newInstance(int pageId, int dataId) {
        Bundle bundle = new Bundle();
        bundle.putInt("pageId", pageId);
        bundle.putInt("dataId", dataId);
        MonitorListFragment fragment = new MonitorListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty)
    TextView mEmptyView;
    private View mRootView;
    private List<UserStations.DataBean.ListBean> mStationsData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mGateawysData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mMonitoringsData = new ArrayList<>();
    private StationsAdapter mStationsAdapter;
    private int mCurrentPage = 0;
    private static final int PAGE_SIZE = 30;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentPage = getArguments().getInt("pageId");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.frag_monitor_list, container, false);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initView();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toGetData(getArguments().getInt("dataId"));
    }

    private void initView() {
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            toGetData(getArguments().getInt("dataId"));
        });
        switch (mCurrentPage) {
            case Contanst.PAGE_STATIONS:
                mStationsAdapter = new StationsAdapter(getContext(), mStationsData);
                break;
            case Contanst.PAGE_GATEWAYS:
                mStationsAdapter = new StationsAdapter(getContext(), mGateawysData);
                break;
            case Contanst.PAGE_MONITORS:
                mStationsAdapter = new StationsAdapter(getContext(), mMonitoringsData);
                break;
            default:
                return;
        }
        mRecyclerView.addItemDecoration(new BaseItemDecoration(getContext()));
        mRecyclerView.setAdapter(mStationsAdapter);
        mStationsAdapter.setOnItemClickListener((view, position, data) -> {
            if (!data.isHasChild()) {
                if (mCurrentPage == Contanst.PAGE_MONITORS) {
                    startActivity(new Intent(getContext(), DetailActivity.class)
                            .putExtra("mMonitoringId", data.getId()));
                }
                return;
            }
            // 特别注意: mCurrentPage不能使用++运算符
            BusProvider.get().post(new EventFragmentNav(mCurrentPage + 1, data.getId()));
        });
    }

    /**
     * 2.获取网关下的监测点
     */
    private void getMonitoringByGateWayId(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getMonitoringByGateWayId(id, 1, PAGE_SIZE);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (response != null) {
                    mMonitoringsData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mMonitoringsData);
                } else {
                    AndroidUtils.showEmptyNull(mEmptyView);
                }
            }

            @Override
            public void onFailure(Call<UserStations> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                AndroidUtils.showEmptyError(mEmptyView);
            }
        });
    }

    /**
     * 1.获取站点下的网关
     */
    private void getGateways(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getGateways(id, 1, PAGE_SIZE);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (response != null) {
                    mGateawysData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mGateawysData);
                } else {
                    AndroidUtils.showEmptyNull(mEmptyView);
                }
            }

            @Override
            public void onFailure(Call<UserStations> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                AndroidUtils.showEmptyError(mEmptyView);
            }
        });
    }

    /**
     * 0.获取站点
     */
    private void getStations() {
        int userId = UserUtils.get().getUserId(getContext());
        if (userId == -1) {
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getStations(userId, 1, PAGE_SIZE);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (response == null) {
                    AndroidUtils.showEmptyNull(mEmptyView);
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        List<UserStations.DataBean.ListBean> datas = response.body().getData().getList();
                        mStationsData = datas;
                        mStationsAdapter.setDatas(mStationsData);
                    }
                } else {
                    AndroidUtils.showEmptyNull(mEmptyView);
                    Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<UserStations> call, @Nullable Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                AndroidUtils.showEmptyError(mEmptyView);
                if (mStationsData.size() == 0) {
                    Toast.makeText(getContext(), "服务器异常 " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 分发数据获取
     */
    public void toGetData(int stationId) {
        mSwipeRefreshLayout.setRefreshing(true);
        switch (mCurrentPage) {
            case Contanst.PAGE_STATIONS:
                if (mStationsData.size() == 0)
                    getStations();
                else mSwipeRefreshLayout.setRefreshing(false);
                break;
            case Contanst.PAGE_GATEWAYS:
                if (mGateawysData.size() == 0)
                    getGateways(stationId);
                else mSwipeRefreshLayout.setRefreshing(false);
                break;
            case Contanst.PAGE_MONITORS:
                if (mMonitoringsData.size() == 0)
                    getMonitoringByGateWayId(stationId);
                else mSwipeRefreshLayout.setRefreshing(false);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
