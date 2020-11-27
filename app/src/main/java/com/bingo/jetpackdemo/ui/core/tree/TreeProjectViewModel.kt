package com.bingo.jetpackdemo.ui.core.tree

import android.util.Log
import androidx.lifecycle.*
import com.bingo.jetpackdemo.AppException
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.ListData
import com.bingo.jetpackdemo.data.entity.wan.TreeItem
import com.bingo.jetpackdemo.data.entity.wan.WanResponse
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TreeProjectViewModel : ViewModel() {

    val loading = MutableLiveData<Loading.State>()
    val loadingProject = MutableLiveData<Loading.State>()


    fun projectTree(): LiveData<List<TreeItem>> {
        return WanRepository.getInstance()
            .projectTree()
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

    fun projectList(pageNum: Int, cid: String): LiveData<ListData<Article>> {
        return WanRepository.getInstance()
            .projectList(pageNum, cid)
            .onStart {
                loadingProject.postValue(Loading.State.start)
            }
            .onCompletion {
                loadingProject.postValue(Loading.State.dismiss)
            }
            .catch { cause ->
                if (cause is AppException) {
                    loadingProject.postValue(Loading.State.fail(cause))
                } else {
                    val appException = AppException(cause)
                    loadingProject.postValue(Loading.State.fail(appException))
                }
            }
            .asLiveData()
    }

}