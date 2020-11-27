package com.bingo.jetpackdemo.data

import androidx.lifecycle.LiveData
import com.bingo.jetpackdemo.data.entity.wan.*
import kotlinx.coroutines.flow.Flow

interface IWanRepository {

    fun banners(): Flow<List<Banner>>

    fun article(pageNum: Int, cid: String? = null): Flow<ListData<Article>>

    fun tree(): Flow<List<TreeItem>>

    fun projectTree(): Flow<List<TreeItem>>

    fun projectList(pageNum: Int, cid: String? = null): Flow<ListData<Article>>

    fun hotKey(): Flow<List<HotKey>>

    fun articleTop(): Flow<List<Article>>

    fun query(pageNum: Int, k: String): Flow<ListData<Article>>

    fun navi(): Flow<List<Navi>>

}