package com.shenhua.outer.security.report.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.BaseItemDecoration;
import com.shenhua.outer.security.report.adapter.StationsAdapter;
import com.shenhua.outer.security.report.bean.UserStations;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
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
public class MonitorFragment extends Fragment {

    public static MonitorFragment newInstance() {
        return new MonitorFragment();
    }

    private View mRootView;
    private boolean isLoaded;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private StationsAdapter mStationsAdapter;
    private List<UserStations.DataBean.ListBean> mStationsData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mGateawysData = new ArrayList<>();
    private List<UserStations.DataBean.ListBean> mMonitoringsData = new ArrayList<>();
    private int current = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.frag_monitor, container, false);
            ButterKnife.bind(this, mRootView);
            initView();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    private void initView() {
        if (mStationsAdapter == null) {
            mStationsAdapter = new StationsAdapter(getContext(), mStationsData);
        }
        mRecyclerView.addItemDecoration(new BaseItemDecoration(getContext()));
        mRecyclerView.setAdapter(mStationsAdapter);
        mStationsAdapter.setOnItemClickListener((view, position, data) -> {
            if (current == 0) {
                getGateways(data.getId());
            } else if (current == 1) {
                getMonitoringByGateWayId(data.getId());
            } else if (current == 2) {
                startActivity(new Intent(getContext(), DetailActivity.class)
                        .putExtra("monitoringId", data.getId()));
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && !isLoaded) {
            getStations();
        }
    }

    /**
     * 获取网关下的监测点
     */
    private void getMonitoringByGateWayId(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getMonitoringByGateWayId(id, 1, 10);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response != null) {
                    mMonitoringsData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mMonitoringsData);
                    mStationsAdapter.notifyDataSetChanged();
                    current = 2;
                }
            }

            @Override
            public void onFailure(Call<UserStations> call, Throwable t) {

            }
        });
    }

    /**
     * 获取站点下的网关
     */
    private void getGateways(int id) {
        Call<UserStations> call = RetrofitHelper.get().getRetrofit().create(IService.class).getGateways(id, 1, 10);
        call.enqueue(new Callback<UserStations>() {
            @Override
            public void onResponse(@Nullable Call<UserStations> call, @Nullable Response<UserStations> response) {
                if (response != null) {
                    mGateawysData = response.body().getData().getList();
                    mStationsAdapter.setDatas(mGateawysData);
                    mStationsAdapter.notifyDataSetChanged();
                    current = 1;
                }
            }

            @Override
            public void onFailure(Call<UserStations> call, Throwable t) {

            }
        });
    }

    /**
     * 获取站点
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
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        List<UserStations.DataBean.ListBean> datas = response.body().getData().getList();
                        mStationsData = datas;
                        mStationsAdapter.setDatas(mStationsData);
                        mStationsAdapter.notifyDataSetChanged();
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
        isLoaded = true;
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
