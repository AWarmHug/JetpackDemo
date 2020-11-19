package com.bingo.jetpackdemo.ui.widget.recyclerview.adpter;


import com.bingo.jetpackdemo.ui.widget.OnItemClickListener;
import com.bingo.jetpackdemo.ui.widget.OnItemLongClickListener;
import com.bingo.jetpackdemo.ui.widget.recyclerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 51hs_android
 * 时间: 2017/3/7
 * 简介: RecycleView.Adapter基类，添加几个数据添加刷新去除方法和点击事件,如果添加了头部，需要额外处理，并没有处理头部的情况
 */

public abstract class ClickAdapter<T, VH extends BaseViewHolder> extends BaseAdapter<T, VH> {

    protected OnItemClickListener mOnItemClickListener;

    protected OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
        holder.setItemClickListener(mOnItemClickListener, mOnItemLongClickListener, getHeaderCount());
        onBindItem(holder, position, payloads);
    }

    //不使用这个
    @Override
    public final void onBindViewHolder(VH holder, int position) {
        onBindItem(holder, position, new ArrayList<>());
        holder.setItemClickListener(mOnItemClickListener, mOnItemLongClickListener, getHeaderCount());
    }


    protected abstract void onBindItem(VH holder, int position, List<Object> payloads);


}
