package com.bingo.jetpackdemo.ui.core.home

import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.Banner
import com.bingo.jetpackdemo.data.entity.wan.ListData

data class HomeContent(val banners: List<Banner>, val articles: ListData<Article>)