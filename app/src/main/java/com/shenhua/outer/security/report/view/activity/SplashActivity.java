package com.shenhua.outer.security.report.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.shenhua.outer.security.report.BuildConfig;
import com.shenhua.outer.security.report.core.utils.UserUtils;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!BuildConfig.DEBUG && BuildConfig.ENV_TYPE > 0 && System.currentTimeMillis() > 11507651199000L) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("App授权失败，请使用正式授权版本");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", (dialog, which) -> finish());
            builder.show();
            return;
        }

        if (UserUtils.get().isLogin(this)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
