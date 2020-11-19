package com.bingo.jetpackdemo.ui.core.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.databinding.SearchActivityPopularArticleItemBinding
import com.bingo.jetpackdemo.databinding.SearchActivityPopularKeyItemBinding

class HotKeyView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {
    val binding: SearchActivityPopularKeyItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_activity_popular_key_item, this, true
    )

}

class HotArticleView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {
    val binding: SearchActivityPopularArticleItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_activity_popular_article_item, this, true
    )

}