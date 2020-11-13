package com.bingo.jetpackdemo.ui.core.girl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.EmptyException
import com.bingo.jetpackdemo.ResultException
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.remote.GankService
import kotlinx.coroutines.flow.*

class GirlViewModel : ViewModel() {

    fun data(category: String, type: String, page: Int): LiveData<List<Article>> = flow {
        val result = GankService.create().data(category, type, page)
        if (result.isSuccess) {
            result.data?.apply {
                if (isNotEmpty()) {
                    emit(this)
                } else {
                    throw EmptyException()
                }
            }
        } else {
            throw ResultException()
        }
    }
        .catch { cause ->

        }
        .asLiveData()


    fun <T> check(result: Result<T>, block: () -> Unit) {
        if (result.isSuccess) {
            result.data?.let {
                block()
            } ?: let {
                throw EmptyException()
            }


        } else {
            throw ResultException()
        }
    }
}