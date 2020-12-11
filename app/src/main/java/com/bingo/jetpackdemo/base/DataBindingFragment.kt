package com.bingo.jetpackdemo.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.bingo.jetpackdemo.ui.core.tree.gank.TypeItemViewModel
import com.bingo.jetpackdemo.ui.core.tree.TreeArticleViewModel

open class DataBindingFragment : Fragment() {

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    open fun getTitle(): String {
        return ""
    }

}