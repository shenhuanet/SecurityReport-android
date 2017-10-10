package com.shenhua.outer.security.report.adapter;

import android.content.Context;
import android.view.View;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.WarningList;

import java.util.List;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class WarningListAdapter extends BaseRecyclerAdapter<WarningList.DataBean.ListBean> {

    public WarningListAdapter(Context context, List<WarningList.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.item_warning;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, WarningList.DataBean.ListBean item) {
        holder.setText(R.id.tvName, item.getStationName());
        holder.setText(R.id.tvGateway, " -- " + item.getGatewayName());
        holder.setText(R.id.monitoringName, item.getMonitoringName());
        holder.setText(R.id.tvType, item.getTypeName());
        setTypeBackground(holder.getView(R.id.tvType), item.getWarningStatus());
    }

    /**
     * 设置类型的背景
     *
     * @param view          textview
     * @param warningStatus 报警状态
     */
    private void setTypeBackground(View view, int warningStatus) {
        int bgId = 0;
        switch (warningStatus) {
            case 0:
                bgId = R.drawable.item_rect_green;
                break;
            case 1:
                bgId = R.drawable.item_rect_red;
                break;
            case 2:
                bgId = R.drawable.item_rect_gray;
                break;
            case 3:
                bgId = R.drawable.item_rect_gray1;
                break;
            case 4:
                bgId = R.drawable.item_rect_orange;
                break;
        }
        view.setBackgroundResource(bgId);
    }

}
