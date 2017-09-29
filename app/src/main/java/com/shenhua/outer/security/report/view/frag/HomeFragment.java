package com.shenhua.outer.security.report.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ldf.calendar.component.CalendarDate;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.component.Utils;
import com.ldf.calendar.listener.OnSelectDateListener;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;
import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.view.widget.CustomDayView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页页面
 * Created by shenhua on 2017-09-26-0026.
 * Email shenhuanet@126.com
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private View mRootView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tvToolbarTitle)
    TextView mToolbarTitleTv;
    @BindView(R.id.frameOverview)
    FrameLayout mOverviewFrame;
    @BindView(R.id.frameMonitor)
    FrameLayout mMonitorFrame;
    @BindView(R.id.calendarView)
    MonthPager mMonthPager;

    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private CalendarViewAdapter calendarAdapter;
    private CalendarDate currentDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.frag_home, container, false);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initView();
        initCalendar();
        return mRootView;
    }

    private void initView() {
        getFragmentManager().beginTransaction().add(R.id.frameOverview, new HomeOverViewFragment())
                .commit();
        getFragmentManager().beginTransaction().add(R.id.frameMonitor, new HomeAlarmMonitorFragment())
                .commit();
    }

    private void initCalendar() {
        currentDate = new CalendarDate();
        CustomDayView customDayView = new CustomDayView(getContext(), R.layout.view_custom_day);
        calendarAdapter = new CalendarViewAdapter(getContext(), customDayView, new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                currentDate = date;
                onDateChanged(date, true);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                mMonthPager.selectOtherMonth(offset);
            }
        });
        mMonthPager.setAdapter(calendarAdapter);
        mMonthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        mMonthPager.setPageTransformer(false, (page, position) -> {
            position = (float) Math.sqrt(1 - Math.abs(position));
            page.setAlpha(position);
        });
        mMonthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                mCurrentPage = position;
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    currentDate = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    onDateChanged(currentDate, false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // 首次刷新时用于正确显示当日
        ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
        viewTreeObserver.addOnWindowFocusChangeListener(hasFocus -> {
            CalendarDate today = new CalendarDate();
            calendarAdapter.notifyDataChanged(today);
        });
    }

    /**
     * 日历日期被选择时调用
     *
     * @param date 选中的日期
     */
    private void onDateChanged(CalendarDate date, boolean needRefresh) {
        if (date == null) {
            return;
        }
        if (needRefresh) {
            // 网络请求
            Toast.makeText(getContext(), date.getYear() + "-" + date.getMonth() + "-" + date.getDay(), Toast.LENGTH_SHORT).show();
        }
        mToolbarTitleTv.setText(Utils.sMonth[date.getMonth() - 1]);
    }

    @OnClick({R.id.tvPre, R.id.tvNext})
    void changePage(View view) {
        if (view.getId() == R.id.tvPre) {
            mMonthPager.setCurrentItem(mMonthPager.getCurrentPosition() + -1);
        } else {
            mMonthPager.setCurrentItem(mMonthPager.getCurrentPosition() + 1);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
