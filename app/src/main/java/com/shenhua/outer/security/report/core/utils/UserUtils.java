package com.shenhua.outer.security.report.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.shenhua.outer.security.report.bean.User;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class UserUtils {

    private static UserUtils sInstance = null;
    private static final String SP_NAME = "user";

    public synchronized static UserUtils get() {
        if (sInstance == null) {
            sInstance = new UserUtils();
        }
        return sInstance;
    }

    public synchronized void saveUser(Context context, User.DataBean user) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit()
                .putInt("id", user.getId())
                .putString("name", user.getName())
                .putString("email", user.getEmail())
                .putString("tel", user.getTel())
                .putString("salt", user.getSalt())
                .putString("openId", user.getOpenId())
                .putInt("roleId", user.getRoleId())
                .putString("roleName", user.getRoleName())
                .apply();
    }

    public synchronized User.DataBean obtainUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        User.DataBean bean = new User.DataBean();
        if (TextUtils.isEmpty(sp.getString("name", ""))) {
            return null;
        }
        bean.setId(sp.getInt("id", -1));
        bean.setName(sp.getString("name", ""));
        bean.setEmail(sp.getString("email", ""));
        bean.setTel(sp.getString("tel", ""));
        bean.setSalt(sp.getString("salt", ""));
        bean.setOpenId(sp.getString("openId", ""));
        bean.setRoleId(sp.getInt("roleId", -1));
        bean.setRoleName(sp.getString("roleName", ""));
        return bean;
    }

    public synchronized int getUserId(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getInt("id", -1);
    }

    public synchronized boolean isLogin(Context context) {
        return obtainUser(context) != null;
    }

    public synchronized void logout(Context context) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().clear().apply();
    }
}
