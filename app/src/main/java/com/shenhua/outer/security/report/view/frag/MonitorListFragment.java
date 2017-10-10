package com.shenhua.outer.security.report.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private UserStations.DataBean mStationsDatabean;
    private UserStations.DataBean mGateawysDatabean;
    private UserStations.DataBean mMonitoringsDatabean;
    private List<UserStations.DataBean.ListBean> mStationsData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mGateawysData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mMonitoringsData = new ArrayList<>();
    private int mStationsPageNum = 1, mGateawysPageNum = 1, mMonitoringsPageNum = 1;
    private StationsAdapter mStationsAdapter;
    private int mCurrentPage = 0;
    private static final int PAGE_SIZE = 30;
    private boolean isLoadingMore = false;

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
        getAdapter();
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

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                        findLastVisibleItemPosition();
                int count = getCount();
                if (lastVisibleItemPosition + 1 == count) {
                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mStationsAdapter.notifyItemRemoved(count);
                    }
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        new Handler().postDelayed(() -> {
                            getMoreData();
                            isLoadingMore = false;
                        }, 1000);
                    }
                }
            }
        });
    }

    private void getAdapter() {
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
        }
    }

    private int getCount() {
        int count = 0;
        switch (mCurrentPage) {
            case Contanst.PAGE_STATIONS:
                count = mStationsData.size();
                break;
            case Contanst.PAGE_GATEWAYS:
                count = mGateawysData.size();
                break;
            case Contanst.PAGE_MONITORS:
                count = mMonitoringsData.size();
                break;
        }
        return count;
    }

    private void getMoreData() {
        if (hasMore()) {
            switch (mCurrentPage) {
                case Contanst.PAGE_STATIONS:
                    getStationsMore();
                    break;
                case Contanst.PAGE_GATEWAYS:
                    getGatewaysMore(getArguments().getInt("dataId"));
                    break;
                case Contanst.PAGE_MONITORS:
                    getMonitoringByGateWayIdMore(getArguments().getInt("dataId"));
                    break;
            }
        }
    }

    private boolean hasMore() {
        UserStations.DataBean data = null;
        switch (mCurrentPage) {
            case Contanst.PAGE_STATIONS:
                data = mStationsDatabean;
                break;
            case Contanst.PAGE_GATEWAYS:
                data = mGateawysDatabean;
                break;
            case Contanst.PAGE_MONITORS:
                data = mMonitoringsDatabean;
                break;
        }
        return data != null && data.isHasNextPage();
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
                    mMonitoringsDatabean = response.body().getData();
                    mMonitoringsData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mMonitoringsData);
                    reset();
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
     * 2.获取网关下的监测点更多
     */
    private void getMonitoringByGateWayIdMore(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class)
                .getMonitoringByGateWayId(id, mMonitoringsPageNum++, PAGE_SIZE);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response != null) {
                    mMonitoringsDatabean = response.body().getData();
                    mMonitoringsData.addAll(response.body().getData().getList());
                    mStationsAdapter.addMoreItem(response.body().getData().getList());
                }
            }

            @Override
            public void onFailure(Call<UserStations> call, Throwable t) {

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
                    mGateawysDatabean = response.body().getData();
                    mGateawysData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mGateawysData);
                    reset();
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
     * 1.获取站点下的网关更多
     */
    private void getGatewaysMore(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getGateways(id, mGateawysPageNum++, PAGE_SIZE);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response != null) {
                    mGateawysDatabean = response.body().getData();
                    mGateawysData.addAll(response.body().getData().getList());
                    mStationsAdapter.addMoreItem(response.body().getData().getList());
                }
            }

            @Override
            public void onFailure(Call<UserStations> call, Throwable t) {

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
                Log.d("shenhuaLog -- " + MonitorListFragment.class.getSimpleName(), "站点 :  onResponse: >> " + response.raw().toString());
                mSwipeRefreshLayout.setRefreshing(false);
                if (response == null) {
                    AndroidUtils.showEmptyNull(mEmptyView);
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        mStationsDatabean = response.body().getData();
                        List<UserStations.DataBean.ListBean> datas = response.body().getData().getList();
                        mStationsData = datas;
                        mStationsAdapter.setDatas(mStationsData);
                    }
                    reset();
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
     * 0.获取站点更多
     */
    private void getStationsMore() {
        int userId = UserUtils.get().getUserId(getContext());
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getStations(userId, mStationsPageNum++, PAGE_SIZE);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response.body().getData() != null) {
                    mStationsDatabean = response.body().getData();
                    List<UserStations.DataBean.ListBean> datas = response.body().getData().getList();
                    mStationsData.addAll(datas);
                    mStationsAdapter.addMoreItem(datas);
                }
            }

            @Override
            public void onFailure(@Nullable Call<UserStations> call, @Nullable Throwable t) {
            }
        });
    }

    /**
     * 分发数据获取,初始化或刷新操作
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

    private void reset() {
        switch (mCurrentPage) {
            case Contanst.PAGE_STATIONS:
                mStationsPageNum = 1;
                break;
            case Contanst.PAGE_GATEWAYS:
                mGateawysPageNum = 1;
                break;
            case Contanst.PAGE_MONITORS:
                mMonitoringsPageNum = 1;
                break;
        }
    }
}
