package com.bingo.jetpackdemo.data

import android.util.Log
import com.bingo.jetpackdemo.ResultException
import com.bingo.jetpackdemo.data.entity.wan.*
import com.bingo.jetpackdemo.data.remote.WanAndroidService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WanRepository(val wan: WanAndroidService) : IWanRepository {


    companion object {
        private val wanRepository: WanRepository = WanRepository(WanAndroidService.create())
        fun getInstance(): IWanRepository {
            return wanRepository
        }

    }

    override fun banners(): Flow<WanResponse<List<Banner>>> = flow {
        val wanResponse = wan.banner()
        emit(wanResponse)
    }

    override fun article(pageNum: Int, cid: String?): Flow<ListData<Article>> = flow {
        val wanResponse = wan.article(pageNum, cid)
        if (wanResponse.isSuccess) {
            emit(wanResponse.data)
        } else {
            throw ResultException()
        }
    }

    override fun tree(): Flow<List<TreeItem>> = flow {
        val wanResponse = wan.tree()
        if (wanResponse.isSuccess) {
            emit(wanResponse.data)
        } else {
            throw ResultException()
        }
    }

    override fun projectTree(): Flow<List<TreeItem>> = flow {
        val wanResponse = wan.projectTree()
        if (wanResponse.isSuccess) {
            wanResponse.data.map {
                it.name = it.name.replace("amp;", "")
                return@map it
            }

            emit(wanResponse.data)
        } else {
            throw ResultException()
        }
    }

    override suspend fun projectList(pageNum: Int, cid: String?): Flow<ListData<Article>> = flow {
        val data = wan.projectList(pageNum, cid).peeling()
        emit(data)
    }.flowOn(Dispatchers.IO)

    override fun hotKey(): Flow<List<HotKey>> = flow {
        val data = wan.hotKey().peeling()
        emit(data)
    }

    override fun articleTop(): Flow<List<Article>> = flow {
        val data = wan.articleTop().peeling()
        Log.d("*********", "articleTop: ")
        emit(data)
    }

    override fun query(pageNum: Int, k: String): Flow<ListData<Article>> = flow {
        val data = wan.query(pageNum, k).peeling()
        Log.d("*********", "query: "+data)
        emit(data)
    }


    private fun <T> WanResponse<T>.peeling(): T {
        if (isSuccess) {
            return data
        } else {
            throw ResultException()
        }
    }

}