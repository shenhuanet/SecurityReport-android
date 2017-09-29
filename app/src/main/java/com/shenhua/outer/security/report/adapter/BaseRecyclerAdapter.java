package com.shenhua.outer.security.report.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView通用适配器
 * Created by Shenhua on 8/21/2016.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerViewHolder> {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected OnItemClickListener<T> mOnItemClickListener;
    protected OnItemLongClickListener<T> mOnItemLongClickListener;

    public BaseRecyclerAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(getItemViewId(viewType), parent, false), viewType);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(v, holder.getLayoutPosition(), mDatas.get(holder.getAdapterPosition()));
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemLongClickListener.OnItemLongClick(view, holder.getLayoutPosition(), mDatas.get(holder.getAdapterPosition()));
                    return true;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        bindData(holder, position, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void addItem(int position, T itemData) {
        mDatas.add(position, itemData);
        notifyItemInserted(position);
    }

    public void deleteItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void addMoreItem(List<T> datas) {
        int startPosition = mDatas.size();
        mDatas.addAll(datas);
        notifyItemRangeChanged(startPosition, mDatas.size());
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public abstract int getItemViewId(int viewType);

    public abstract void bindData(BaseRecyclerViewHolder holder, int position, T item);

    public void setOnItemClickListener(OnItemClickListener<T> clickListener) {
        this.mOnItemClickListener = clickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> longClickListener) {
        this.mOnItemLongClickListener = longClickListener;
    }

    public interface OnItemClickListener<T> {
        void OnItemClick(View view, int position, T data);
    }

    public interface OnItemLongClickListener<T> {
        boolean OnItemLongClick(View view, int position, T data);
    }

    public static class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

        //集合类，layout里包含的View,以view的id作为key，value是view对象
        private SparseArray<View> mViews;
        private Context mContext;
        private int viewType;

        public BaseRecyclerViewHolder(Context context, View itemView, int viewType) {
            super(itemView);
            mContext = context;
            mViews = new SparseArray<>();
            this.viewType = viewType;
        }

        private <T extends View> T findViewById(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public View getView(int viewId) {
            return findViewById(viewId);
        }

        public int getViewType() {
            return viewType;
        }

        public BaseRecyclerViewHolder setText(int viewId, String value) {
            TextView view = findViewById(viewId);
            view.setText(value);
            return this;
        }

        public BaseRecyclerViewHolder setBackground(int viewId, int resId) {
            View view = findViewById(viewId);
            view.setBackgroundResource(resId);
            return this;
        }

        public BaseRecyclerViewHolder setBackgroundColor(int viewId, int color) {
            View view = findViewById(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public BaseRecyclerViewHolder setImage(int viewId, int resId) {
            ImageView view = findViewById(viewId);
//            Glide.with(mContext).load(resId).centerCrop().into(view);
            return this;
        }

        public BaseRecyclerViewHolder setImage(int viewId, String url) {
            ImageView view = findViewById(viewId);
//            Glide.with(mContext).load(url).centerCrop().into(view);
            return this;
        }

        public BaseRecyclerViewHolder setImage(int viewId, Drawable drawable) {
            ImageView view = findViewById(viewId);
//            Glide.with(mContext).load(drawable).centerCrop().into(view);
            return this;
        }

        public BaseRecyclerViewHolder setOnItemViewClickListener(int viewId, View.OnClickListener listener) {
            View view = findViewById(viewId);
            view.setOnClickListener(listener);
            return this;
        }

    }

}
