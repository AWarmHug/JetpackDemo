package com.bingo.jetpackdemo.ui.widget.recyclerview.adpter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.bingo.jetpackdemo.R;
import com.bingo.jetpackdemo.ui.widget.recyclerview.BaseViewHolder;

import java.util.List;


/**
 * 作者：warm
 * 时间：2018-10-25 15:55
 * 描述：
 */
public abstract class EmptyAdapter<T> extends ClickAdapter<T, BaseViewHolder> {

    public static final int EMPTY = -99;


    private boolean isShow;
    private String msg;
    private View.OnClickListener clickListener;


    public void loadFinish(String msg, int res) {
        this.isShow = false;
        this.msg = msg;
        this.clickListener = null;
        notifyItemChanged(getItemCount() - 1);
    }


    @Override
    protected final void onBindItem(BaseViewHolder holder, int position, List<Object> payloads) {

        switch (getItemViewType(position)) {
            case EMPTY:
                onBindEmptyItem(holder);
                break;
            default:
                onBindDefaultItem(holder, position - getHeaderCount(), payloads);
                break;
        }
    }

    protected abstract void onBindDefaultItem(BaseViewHolder holder, int position, List<Object> payloads);


    protected void onBindEmptyItem(BaseViewHolder holder) {
        if (holder instanceof EmptyViewHolder) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.showState(isShow, msg, clickListener);
        }
    }


    @NonNull
    @Override
    public final BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case EMPTY:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_view_h, parent, false));
            default:
                return onCreateDefaultViewHolder(parent, viewType);
        }
    }

    protected abstract BaseViewHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType);


    @Override
    public final int getItemViewType(int position) {
        if (isEmpty()) {
            return EMPTY;
        } else {
            return getDefaultItemViewType(position);
        }
    }

    public int getDefaultItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) {
            return 1;
        } else {
            return super.getItemCount();
        }
    }

    static class EmptyViewHolder extends BaseViewHolder {
        LottieAnimationView pb;
        TextView tv;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            pb = itemView.findViewById(R.id.lottie);
            tv = itemView.findViewById(R.id.tvMsg);
        }

        public void showState(boolean isShow, String msg, View.OnClickListener clickListener) {
            pb.setVisibility(isShow ? View.VISIBLE : View.GONE);
            itemView.setOnClickListener(clickListener);
            if (!TextUtils.isEmpty(msg)) {
                tv.setText(msg);
                tv.setVisibility(View.VISIBLE);
            } else {
                tv.setVisibility(View.GONE);
            }
        }
    }

}
