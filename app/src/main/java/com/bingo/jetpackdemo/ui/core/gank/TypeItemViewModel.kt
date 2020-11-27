package com.bingo.jetpackdemo.ui.core.gank

import androidx.lifecycle.*
import com.bingo.jetpackdemo.AppException
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class TypeItemViewModel : ViewModel() {

    val loading = MutableLiveData<Loading.State>()

    fun data(category: String, type: String, page: Int): LiveData<Result<List<Article>>> = flow {
        val result = GankService.create().data(category, type, page)
        emit(result)
    }
        .onStart {
            if (page == 0) {
                loading.postValue(Loading.State.start)
            }
        }
        .onCompletion { cause ->
            if (cause != null) {
                if (cause is AppException) {
                    if (page == 0) {
                        loading.postValue(Loading.State.fail(cause))
                    }
                } else {
                    val appException = AppException(cause)
                    if (page == 0) {
                        loading.postValue(Loading.State.fail(appException))
                    }
                }
            } else {
                loading.postValue(Loading.State.dismiss)
            }
        }
        .catch { cause ->

        }
        .asLiveData()

}