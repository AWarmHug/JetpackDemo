package com.bingo.jetpackdemo.ui.widget.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.databinding.LoadingViewBinding
import com.bingo.jetpackdemo.databinding.SearchViewBinding

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding = DataBindingUtil.inflate<SearchViewBinding>(
        LayoutInflater.from(context),
        R.layout.search_view, this, true
    )


}