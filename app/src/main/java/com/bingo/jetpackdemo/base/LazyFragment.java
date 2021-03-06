package com.bingo.jetpackdemo.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

/**
 * 作者: 51hs_android
 * 时间: 2017/2/22
 * 简介:
 */

public abstract class LazyFragment extends DataBindingFragment {

    /**
     * 判断是不是一次可见,
     */
    private boolean isFirst = true;

    /**
     * 标志inflater是否已经完成
     */
    private boolean isInflate;

    /**
     * 标志 是否已经可见
     */
    private boolean isVisible;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInflate = true;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initPrepare();
    }

    /**
     * 这是一个实际操作方法
     */
    private synchronized void initPrepare() {

        if (isInflate && isVisible) {
            if (isFirst) {
                onFirstVisible();
                isFirst = false;
            } else {
                onVisible();
            }
        } else {
            onInVisible();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            if (isFirst) {
                initPrepare();
            } else {
                initPrepare();
            }
        } else {
            isVisible = false;
            initPrepare();
        }
    }

    /**
     * 用户不可见时.
     */
    protected abstract void onInVisible();

    /**
     * 第一次加载出来,我们可以进行数据加载
     */
    protected abstract void onFirstVisible();

    /**
     * 用户可见时,但不是第一次可见,就是回到从其他页面回到当前页面时.
     */
    protected abstract void onVisible();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
