package com.bingo.jetpackdemo.base

import androidx.lifecycle.*
import com.bingo.jetpackdemo.AppException
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class LoadingViewModel : ViewModel() {

    val loading = MutableLiveData<Loading.State>()



}