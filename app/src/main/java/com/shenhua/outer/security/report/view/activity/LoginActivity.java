package com.shenhua.outer.security.report.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.User;
import com.shenhua.outer.security.report.core.Contanst;
import com.shenhua.outer.security.report.core.IService;
import com.shenhua.outer.security.report.core.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class LoginActivity extends AppCompatActivity {

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contanst.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<User> user = retrofit.create(IService.class).login(username, password, clientId, 1);
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
