package com.shenhua.outer.security.report.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.User;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.RetrofitHelper;
import com.shenhua.outer.security.report.core.service.GetuiIntentService;
import com.shenhua.outer.security.report.core.service.GetuiPushService;
import com.shenhua.outer.security.report.core.utils.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.usernameLayout)
    LinearLayout mUsernameLayout;
    @BindView(R.id.passwordLayout)
    LinearLayout mPasswordLayout;
    @BindView(R.id.etUsername)
    TextInputEditText mUsernameEt;
    @BindView(R.id.etPassword)
    TextInputEditText mPasswordEt;
    @BindView(R.id.btnLogin)
    Button mLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        PushManager.getInstance().initialize(this.getApplicationContext(), GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetuiIntentService.class);
        mUsernameEt.setOnFocusChangeListener((v, hasFocus) -> mUsernameLayout.setSelected(hasFocus));
        mPasswordEt.setOnFocusChangeListener((v, hasFocus) -> mPasswordLayout.setSelected(hasFocus));
    }

    public void login(View view) {
        String username = mUsernameEt.getText().toString();
        String password = mPasswordEt.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        String clientId = getSharedPreferences("push", Context.MODE_PRIVATE).getString("clientId", "");
        if (TextUtils.isEmpty(clientId)) {
            Toast.makeText(this, "ClientId未知  请重启应用", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("登录中,请稍候");
        dialog.show();
        Call<User> user = RetrofitHelper.get().getRetrofit().create(IService.class).login(username, password, clientId, 1);
        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(LoginActivity.this, "登录失败:服务器异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                if (response.body().getData() == null) {
                    Toast.makeText(LoginActivity.this, "用户数据异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserUtils.get().saveUser(LoginActivity.this, response.body().getData());
                navToMainActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "登录失败:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
