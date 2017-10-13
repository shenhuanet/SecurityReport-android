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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.BaseItemDecoration;
import com.shenhua.outer.security.report.adapter.WarningListAdapter;
import com.shenhua.outer.security.report.bean.WarningList;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.utils.AndroidUtils;
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

    public static WarningListFragment newInstance() {
        return new WarningListFragment();
    }

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty)
    TextView mEmptyView;

    private static final int PAGE_SIZE = 20;
    private View mRootView;
    private List<WarningList.DataBean.ListBean> mDatas = new ArrayList<>();
    private WarningListAdapter mWarningListAdapter;
    private boolean isLoadingMore = false;
    private int currentPage = 0;
    private WarningList.DataBean mDataBean;

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
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this::initData);
        initData();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mWarningListAdapter.getItemCount()) {
                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mWarningListAdapter.notifyItemRemoved(mWarningListAdapter.getItemCount());
//                        return;
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

    private void getMoreData() {
        if (hasMore()) {
            RetrofitHelper.get().getRetrofit().create(IService.class)
                    .getWarningList(UserUtils.get().getUserId(getContext()), currentPage + 1, PAGE_SIZE, 0)
                    .enqueue(new Callback<WarningList>() {
                        @Override
                        public void onResponse(Call<WarningList> call, Response<WarningList> response) {
                            mDataBean = response.body().getData();
                            mDatas.addAll(mDataBean.getList());
                            mWarningListAdapter.addMoreItem(mDataBean.getList());
                            currentPage = mDataBean.getPageNum();
                        }

                        @Override
                        public void onFailure(Call<WarningList> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        } else {
            Toast.makeText(getContext(), "已无更多数据", Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        mSwipeRefreshLayout.setRefreshing(true);
        if (mDatas.size() == 0) {
            AndroidUtils.showEmptyLoading(mEmptyView);
        }
        RetrofitHelper.get().getRetrofit().create(IService.class)
                .getWarningList(UserUtils.get().getUserId(getContext()), 1, PAGE_SIZE, 0)
                .enqueue(new Callback<WarningList>() {
                    @Override
                    public void onResponse(Call<WarningList> call, Response<WarningList> response) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mDataBean = null;
                        mDataBean = response.body().getData();
                        mDatas.clear();
                        mDatas = mDataBean.getList();
                        if (mDatas.size() == 0) {
                            AndroidUtils.showEmptyNull(mEmptyView);
                            return;
                        }
                        AndroidUtils.hideEmpty(mEmptyView);
                        mWarningListAdapter.setDatas(mDatas);
                        currentPage = mDataBean.getPageNum();
                    }

                    @Override
                    public void onFailure(Call<WarningList> call, Throwable t) {
                        t.printStackTrace();
                        mSwipeRefreshLayout.setRefreshing(false);
                        AndroidUtils.showEmptyError(mEmptyView);
                    }
                });
    }

    private boolean hasMore() {
        return mDataBean != null && mDataBean.getEndRow() < mDataBean.getTotal();
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
