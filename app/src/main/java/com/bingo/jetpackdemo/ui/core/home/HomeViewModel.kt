package com.bingo.jetpackdemo.ui.core.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.Banner
import com.bingo.jetpackdemo.data.entity.wan.ListData
import com.bingo.jetpackdemo.data.entity.wan.WanResponse
import com.bingo.jetpackdemo.data.remote.WanAndroidService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class HomeViewModel : ViewModel() {

    fun banners(): LiveData<WanResponse<List<Banner>>> {
        return WanRepository.getInstance()
            .banners()
            .catch { cause ->
                Log.d("**********", "banners: $cause")
            }
            .asLiveData()
    }

    fun article(pageNum: Int): LiveData<ListData<Article>> =
        WanRepository.getInstance().article(pageNum,null)
            .catch { cause ->
                Log.d("**********", "article: $cause")
            }
            .asLiveData()


//    fun banners(): LiveData<Result<List<Banner>>> = flow {
//        Log.d(MainActivity.TAG, "onCreate: ${Thread.currentThread()}")
//        try {
//            val response = GankService.create().banners()
//            Log.d(MainActivity.TAG, "onCreate: ${Thread.currentThread()}")
//            emit(response)
//        } catch (e: Exception) {
//            print(e)
//        }
//    }.asLiveData()
//
//    fun random(): LiveData<List<Article>> = flow {
//        val androidResult = GankService.create().random(Category.ARTICLE.api, "Android")
//        val iOSResult = GankService.create().random(Category.ARTICLE.api, "iOS")
//        val flutterResult = GankService.create().random(Category.ARTICLE.api, "Flutter")
//        Log.d("*********", "androidResult: ${androidResult.status},${androidResult.msg}")
//        Log.d("*********", "androidResult: ${iOSResult.status},${iOSResult.msg}")
//        Log.d("*********", "androidResult: ${flutterResult.status},${flutterResult.msg}")
//
//        val list = mutableListOf<Article>()
//        if (androidResult.isSuccess) {
//            list.addAll(androidResult.data!!)
//        }
//        if (iOSResult.isSuccess) {
//            list.addAll(iOSResult.data!!)
//        }
//        if (flutterResult.isSuccess) {
//            list.addAll(flutterResult.data!!)
//        }
//        emit(list)
//    }
//        .catch { cause ->
//            Log.d("*************", "random: " + cause.message)
//        }
//        .asLiveData()


}