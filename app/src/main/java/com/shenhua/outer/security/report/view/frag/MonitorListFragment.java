package com.shenhua.outer.security.report.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.BaseItemDecoration;
import com.shenhua.outer.security.report.adapter.StationsAdapter;
import com.shenhua.outer.security.report.bean.EventFragmentNav;
import com.shenhua.outer.security.report.bean.UserStations;
import com.shenhua.outer.security.report.core.BusProvider;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.utils.Contanst;
import com.shenhua.outer.security.report.core.utils.UserUtils;

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

    public static MonitorListFragment newInstance(int pageId,int dataId) {
        Bundle bundle = new Bundle();
        bundle.putInt("pageId", pageId);
        bundle.putInt("dataId", dataId);
        MonitorListFragment fragment = new MonitorListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private View mRootView;
    private List<UserStations.DataBean.ListBean> mStationsData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mGateawysData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mMonitoringsData = new ArrayList<>();
    private StationsAdapter mStationsAdapter;
    private int mCurrentPage = 0;

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
                return;
            }
            BusProvider.get().post(new EventFragmentNav(mCurrentPage++, data.getId()));
        });
    }

    /**
     * 2.获取网关下的监测点
     */
    private void getMonitoringByGateWayId(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getMonitoringByGateWayId(id, 1, 10);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response != null) {
                    mMonitoringsData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mMonitoringsData);
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
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getGateways(id, 1, 10);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response != null) {
                    mGateawysData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mGateawysData);
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
            return;
        }
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getStations(userId, 1, 10);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response == null) {
                    return;
                }
                Log.d("shenhuaLog -- " + MonitorListFragment.class.getSimpleName(), "onResponse: " + response.raw().toString());
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        List<UserStations.DataBean.ListBean> datas = response.body().getData().getList();
                        mStationsData = datas;
                        mStationsAdapter.setDatas(mStationsData);
                    }
                } else {
                    Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<UserStations> call, @Nullable Throwable t) {
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
        switch (mCurrentPage) {
            case Contanst.PAGE_STATIONS:
                if (mStationsData.size() == 0)
                    getStations();
                break;
            case Contanst.PAGE_GATEWAYS:
                if (mGateawysData.size() == 0)
                    getGateways(stationId);
                break;
            case Contanst.PAGE_MONITORS:
                if (mMonitoringsData.size() == 0)
                    getMonitoringByGateWayId(stationId);
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
