package com.bingo.jetpackdemo.ui.core.tree

import android.util.Log
import androidx.lifecycle.*
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.ListData
import com.bingo.jetpackdemo.data.entity.wan.TreeItem
import com.bingo.jetpackdemo.data.entity.wan.WanResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TreeProjectViewModel : ViewModel() {

    val projectList = MutableLiveData<ListData<Article>>()

    fun projectTree(): LiveData<List<TreeItem>> {
        return WanRepository.getInstance()
            .projectTree()
            .catch {

            }
            .asLiveData()
    }

    fun projectList(pageNum: Int, cid: String) {

//            WanRepository.getInstance()
//                .projectList(pageNum, cid)
//                .onStart {
//                    Log.d("**********", "onStart")
//                }
//                .catch { cause ->
//                    Log.d("**********", "article: $cause")
//                }
//                .collect {
//                    viewModelScope.launch(Dispatchers.Main) {
//                        projectList.postValue(it)
//                    }


    }

}