package com.bingo.jetpackdemo.ui.core.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.AppException
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.dao.AppDataBase
import com.bingo.jetpackdemo.data.dao.SearchContent
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.ListData
import com.bingo.jetpackdemo.data.entity.wan.Navi
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip

class TreeNavigationViewModel : ViewModel() {

    val loading = MutableLiveData<Loading.State>()

    fun navi(): LiveData<List<Navi>> {

        return WanRepository.getInstance()
            .navi()
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


}