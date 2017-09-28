package com.shenhua.outer.security.report.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> mFragmentList, String[] mTitles) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles[position];
    }

}
