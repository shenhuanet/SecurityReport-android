package com.shenhua.outer.security.report.adapter;

import android.content.Context;

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
        holder.setText(R.id.tvGateway, item.getGatewayName());
        holder.setText(R.id.tvType, item.getTypeName());
    }
}
