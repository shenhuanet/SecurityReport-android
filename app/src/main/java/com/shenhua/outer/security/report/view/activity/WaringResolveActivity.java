package com.shenhua.outer.security.report.view.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.WarningList;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.databinding.ActivityWarningResolveBinding;

import org.json.JSONObject;

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
public class WaringResolveActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.etResolve)
    TextInputEditText mResolveEt;
    @BindView(R.id.btnResolve)
    Button mResolveBtn;
    private int mWarningId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWarningResolveBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_warning_resolve);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        WarningList.DataBean.ListBean data = (WarningList.DataBean.ListBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            binding.setData(data);
            mWarningId = data.getId();
        }

        mResolveEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mResolveBtn.setEnabled(!TextUtils.isEmpty(mResolveEt.getText().toString()));
            }
        });
    }

    @OnClick(R.id.btnResolve)
    void resolve() {
        if (mWarningId == -1) {
            return;
        }
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("请稍候");
        dialog.show();
        RetrofitHelper.get().getRetrofit().create(IService.class)
                .resolveWarning(mWarningId, mResolveEt.getText().toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("shenhuaLog -- " + WaringResolveActivity.class.getSimpleName(), "onResponse: >>> " + response.raw().toString());
                dialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response.body());
                    Toast.makeText(WaringResolveActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    if (obj.getBoolean("success")) {
                        mResolveEt.setText("");
                        mResolveBtn.setEnabled(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(WaringResolveActivity.this, "处理失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(WaringResolveActivity.this, "服务器异常:" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
