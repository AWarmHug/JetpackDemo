package com.bingo.jetpackdemo.ui.core.search

import androidx.annotation.Keep
import com.bingo.jetpackdemo.data.dao.SearchContent
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.HotKey

@Keep
data class HotContent(val hotKeys: List<HotKey>, val hotArticles: List<Article>) {
    var searchContents = mutableListOf<SearchContent>()
}