package com.bingo.jetpackdemo.ui.core.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.ui.core.MainActivity
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.entity.Banner
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import com.bingo.jetpackdemo.data.remote.Category
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class HomeViewModel : ViewModel() {

    fun banners(): LiveData<Result<List<Banner>>> = flow {
        Log.d(MainActivity.TAG, "onCreate: ${Thread.currentThread()}")
        try {
            val response = GankService.create().banners()
            Log.d(MainActivity.TAG, "onCreate: ${Thread.currentThread()}")
            emit(response)
        } catch (e: Exception) {
            print(e)
        }
    }.asLiveData()

    fun random(): LiveData<List<Article>> = flow {
        val androidResult = GankService.create().random(Category.ARTICLE.api, "Android")
        val iOSResult = GankService.create().random(Category.ARTICLE.api, "iOS")
        val flutterResult = GankService.create().random(Category.ARTICLE.api, "Flutter")
        Log.d("*********", "androidResult: ${androidResult.status},${androidResult.msg}")
        Log.d("*********", "androidResult: ${iOSResult.status},${iOSResult.msg}")
        Log.d("*********", "androidResult: ${flutterResult.status},${flutterResult.msg}")

        val list = mutableListOf<Article>()
        if (androidResult.isSuccess) {
            list.addAll(androidResult.data!!)
        }
        if (iOSResult.isSuccess) {
            list.addAll(iOSResult.data!!)
        }
        if (flutterResult.isSuccess) {
            list.addAll(flutterResult.data!!)
        }
        emit(list)
    }
        .catch { cause ->
            Log.d("*************", "random: " + cause.message)
        }
        .asLiveData()
}