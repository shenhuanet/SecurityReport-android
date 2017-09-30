package com.shenhua.outer.security.report.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.MonitorInfo;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.view.widget.MonitorTypeLayout;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layoutDetailParent)
    LinearLayoutCompat mDetailParentLayout;
    @BindView(R.id.tvMonitorName)
    TextView mMonitorNameTv;
    @BindView(R.id.btnXiaoyin)
    Button mXiaoyinBtn;
    @BindView(R.id.btnFuwei)
    Button mFuweiBtn;

    private ProgressDialog mProgressDialog;
    private int mMonitoringId;// 操作的sersorId

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        mMonitoringId = getIntent().getIntExtra("mMonitoringId", -1);
        if (mMonitoringId != -1) {
            getDatial(mMonitoringId);
        } else {
            mMonitorNameTv.setText("监测点id为空");
        }
    }

    private void getDatial(int monitoringId) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("数据获取中");
        }
        btnEnable(false);
        mProgressDialog.show();
        Call<MonitorInfo> call = RetrofitHelper.get().getRetrofit().create(IService.class).getMonitoringInfo(monitoringId);
        call.enqueue(new Callback<MonitorInfo>() {
            @Override
            public void onResponse(Call<MonitorInfo> call, Response<MonitorInfo> response) {
                if (response.body().getData() != null) {
                    mMonitorNameTv.setText(response.body().getData().getMonitoring().getName());
                    List<MonitorInfo.DataBean.SensorsBean> sensors = response.body().getData().getSensors();
                    if (sensors.size() > 0) {
                        MonitorInfo.DataBean.SensorsBean sensorsBean;
                        String[] typeKey = getResources().getStringArray(R.array.monitor_type);
                        MonitorTypeLayout layout;
                        for (int i = 0; i < sensors.size(); i++) {
                            sensorsBean = sensors.get(i);
                            // SensorType 附件3
                            // warningTotal 附件2
                            layout = new MonitorTypeLayout(DetailActivity.this,
                                    typeKey[sensorsBean.getSensorType() - 1],
                                    sensorsBean.getWarningTotal(),
                                    sensorsBean.getReadValue());
                            mDetailParentLayout.addView(layout);
                        }
                    }
                    mProgressDialog.dismiss();
                    btnEnable(true);
                }
            }

            @Override
            public void onFailure(Call<MonitorInfo> call, Throwable t) {
                mProgressDialog.dismiss();
                btnEnable(false);
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

    @OnClick(R.id.btnXiaoyin)
    void noiseReduction() {
        mProgressDialog.setMessage("操作中");
        btnEnable(false);
        mProgressDialog.show();
        Call<String> call = RetrofitHelper.get().getRetrofit().create(IService.class).noiseReduction(mMonitoringId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mProgressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response.body());
                    if (obj.getBoolean("success")) {
                        Toast.makeText(DetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "操作失败:" + obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailActivity.this, "数据异常,请重试", Toast.LENGTH_SHORT).show();
                }
                btnEnable(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mProgressDialog.dismiss();
                btnEnable(true);
                Toast.makeText(DetailActivity.this, "服务器异常 " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnFuwei)
    void reset() {
        mProgressDialog.setMessage("操作中");
        btnEnable(false);
        mProgressDialog.show();
        Call<String> call = RetrofitHelper.get().getRetrofit().create(IService.class).reset(mMonitoringId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mProgressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response.body());
                    if (obj.getBoolean("success")) {
                        Toast.makeText(DetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "操作失败:" + obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailActivity.this, "数据异常,请重试", Toast.LENGTH_SHORT).show();
                }
                btnEnable(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mProgressDialog.dismiss();
                btnEnable(true);
                Toast.makeText(DetailActivity.this, "服务器异常 " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void btnEnable(boolean b) {
        mXiaoyinBtn.setEnabled(b);
        mFuweiBtn.setEnabled(b);
    }
}
