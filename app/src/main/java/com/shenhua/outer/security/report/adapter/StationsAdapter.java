package com.shenhua.outer.security.report.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenhua.outer.security.report.R;
import com.shenhua.outer.security.report.bean.UserStations;

import java.util.List;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class StationsAdapter extends BaseRecyclerAdapter<UserStations.DataBean.ListBean> {

    public StationsAdapter(Context context, List<UserStations.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.item_monitor;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, UserStations.DataBean.ListBean item) {
        holder.setText(R.id.tvStationName, TextUtils.isEmpty(item.getName()) ? "未命名" : item.getName());
        ImageView view = (ImageView) holder.getView(R.id.ivStationState);
        view.setImageLevel(item.getStatus() < 0 ? 0 : item.getStatus());
        TextView tvHasChild = (TextView) holder.getView(R.id.tvHasChild);
        tvHasChild.setEnabled(item.isHasChild());
    }
}
