package com.shenhua.outer.security.report.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.BaseItemDecoration;
import com.shenhua.outer.security.report.adapter.WarningListAdapter;
import com.shenhua.outer.security.report.bean.WarningList;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.utils.UserUtils;
import com.shenhua.outer.security.report.view.activity.WaringResolveActivity;

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
public class WarningListFragment extends Fragment {

    private View mRootView;

    public static WarningListFragment newInstance() {
        return new WarningListFragment();
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<WarningList.DataBean.ListBean> mDatas = new ArrayList<>();
    private WarningListAdapter mWarningListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mWarningListAdapter = new WarningListAdapter(getContext(), mDatas);
        mWarningListAdapter.setOnItemClickListener((view, position, data) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data);
            startActivity(new Intent(getContext(), WaringResolveActivity.class).putExtras(bundle));
        });
        mRecyclerView.setAdapter(mWarningListAdapter);
        mRecyclerView.addItemDecoration(new BaseItemDecoration(getContext()));
        initData();
    }

    private void initData() {
        RetrofitHelper.get().getRetrofit().create(IService.class)
                .getWarningList(UserUtils.get().getUserId(getContext()), 1, 20)
                .enqueue(new Callback<WarningList>() {
                    @Override
                    public void onResponse(Call<WarningList> call, Response<WarningList> response) {
                        mDatas = response.body().getData().getList();
                        mWarningListAdapter.setDatas(mDatas);
                        mWarningListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<WarningList> call, Throwable t) {

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
    }
}
