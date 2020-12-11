package com.bingo.jetpackdemo.ui.core.tree.gank

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.ui.core.MainActivity
import com.bingo.jetpackdemo.data.entity.Banner
import com.bingo.jetpackdemo.data.entity.Type
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class TypeViewModel : ViewModel() {

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

    fun categories(category: String): LiveData<Result<List<Type>>> = flow {
        val response = GankService.create().categories(category)
        emit(response)
    }.asLiveData()

}