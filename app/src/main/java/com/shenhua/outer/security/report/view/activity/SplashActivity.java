package com.shenhua.outer.security.report.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shenhua.outer.security.report.core.utils.UserUtils;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UserUtils.get().isLogin(this)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
