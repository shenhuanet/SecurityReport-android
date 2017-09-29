package com.shenhua.outer.security.report.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.adapter.BaseItemDecoration;
import com.shenhua.outer.security.report.adapter.WarningListAdapter;
import com.shenhua.outer.security.report.bean.WarningList;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class WarningActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<WarningList.DataBean.ListBean> mDatas = new ArrayList<>();
    private WarningListAdapter mWarningListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.shenhua.outer.security.report.R.layout.frag_monitor);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mWarningListAdapter = new WarningListAdapter(this, mDatas);
        mWarningListAdapter.setOnItemClickListener((view, position, data) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data);
            startActivity(new Intent(this, WaringResolveActivity.class).putExtras(bundle));
        });
        mRecyclerView.setAdapter(mWarningListAdapter);
        mRecyclerView.addItemDecoration(new BaseItemDecoration(this));
        initData();
    }

    private void initData() {
        RetrofitHelper.get().getRetrofit().create(IService.class)
                .getWarningList(UserUtils.get().getUserId(this), 1, 20)
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
