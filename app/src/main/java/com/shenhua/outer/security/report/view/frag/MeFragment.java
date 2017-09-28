package com.shenhua.outer.security.report.view.frag;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shenhua.outer.security.report.BR;
import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.User;
import com.shenhua.outer.security.report.core.UserUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的页面
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class MeFragment extends Fragment {

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    private View mRootView;
    private ViewDataBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_me, container, false);
            mRootView = mBinding.getRoot();
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initUser();
        return mRootView;
    }

    private void initUser() {
        User.DataBean user = UserUtils.get().obtainUser(getContext());
        mBinding.setVariable(BR.user, user);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.logout)
    void logout(View view) {
        Toast.makeText(getContext(), "退出登录", Toast.LENGTH_SHORT).show();
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
