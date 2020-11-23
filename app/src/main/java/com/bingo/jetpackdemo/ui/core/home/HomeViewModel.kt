package com.bingo.jetpackdemo.ui.core.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.AppException
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.Banner
import com.bingo.jetpackdemo.data.entity.wan.ListData
import com.bingo.jetpackdemo.data.entity.wan.WanResponse
import com.bingo.jetpackdemo.data.remote.WanAndroidService
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.flow.*

class HomeViewModel : ViewModel() {

    val loading = MutableLiveData<Loading.State>()

    fun homeContent(pageNum: Int): LiveData<HomeContent> {
        val bannersFlow = WanRepository.getInstance()
            .banners()
        val articlesFlow = WanRepository.getInstance()
            .article(pageNum, null)

        return bannersFlow.zip(articlesFlow) { banners, articles ->
            return@zip HomeContent(banners, articles)
        }
            .onStart {
                loading.postValue(Loading.State.start)
            }
            .onCompletion {
                loading.postValue(Loading.State.dismiss)
            }
            .catch { cause ->
                if (cause is AppException) {
                    loading.postValue(Loading.State.fail(cause))
                } else {
                    val appException = AppException(cause)
                    loading.postValue(Loading.State.fail(appException))
                }
            }
            .asLiveData()
    }

    fun article(pageNum: Int): LiveData<ListData<Article>> =
        WanRepository.getInstance().article(pageNum, null)
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