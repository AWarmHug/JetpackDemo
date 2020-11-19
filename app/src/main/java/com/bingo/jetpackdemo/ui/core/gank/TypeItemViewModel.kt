package com.bingo.jetpackdemo.ui.core.gank

import androidx.lifecycle.*
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import kotlinx.coroutines.flow.*

class TypeItemViewModel : ViewModel() {


    fun data(category: String, type: String, page: Int): LiveData<Result<List<Article>>> = flow {
        try {
            val result = GankService.create().data(category, type, page)
            emit(result)
        }catch ( cause:Exception){
            print(cause.toString())

        }
    }
        .catch { cause ->

        }
        .asLiveData()

}