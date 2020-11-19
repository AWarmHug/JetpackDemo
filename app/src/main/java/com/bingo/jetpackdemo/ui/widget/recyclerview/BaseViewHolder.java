package com.bingo.jetpackdemo.ui.widget.recyclerview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bingo.jetpackdemo.ui.widget.OnItemClickListener;
import com.bingo.jetpackdemo.ui.widget.OnItemLongClickListener;


/**
 * 作者：warm
 * 时间：2017-12-26 12:50
 * 描述：
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setItemClickListener(final OnItemClickListener itemClickListener, final OnItemLongClickListener itemLongClickListener, final int headerNum) {
        //头部的数量，当我当前的位置>（头部数量-1）才是我真实的位置，尾部可以暂时不考虑
        if (itemClickListener != null && getAdapterPosition() > (headerNum - 1)) {
            itemView.setOnClickListener(v -> itemClickListener.onItemClick(getAdapterPosition() - headerNum));
        }
        if (itemLongClickListener != null && getAdapterPosition() > (headerNum - 1)) {
            itemView.setOnLongClickListener(v -> {
                itemLongClickListener.onItemLongClick(getAdapterPosition() - headerNum);
                return true;
            });
        }
    }


}