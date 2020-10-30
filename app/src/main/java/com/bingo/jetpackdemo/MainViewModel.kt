package com.bingo.jetpackdemo

import android.util.Log
import androidx.lifecycle.*
import com.bingo.jetpackdemo.data.entity.Banner
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {


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

}