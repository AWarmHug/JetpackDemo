package com.bingo.jetpackdemo.ui.core.search

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
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip

class SearchViewModel : ViewModel() {

    val loading = MutableLiveData<Loading.State>()

    fun hotContent(): LiveData<HotContent> {
        val recentFlow = AppDataBase.getInstance().searchContentDao().getAllSearchContent()
        val articleFlow = WanRepository.getInstance()
            .articleTop()
        val hotKeyFlow = WanRepository.getInstance()
            .hotKey()

        return hotKeyFlow
            .zip(articleFlow) { hotKey, article ->
                return@zip HotContent(hotKey, article)
            }
            .zip(recentFlow) { hotContent: HotContent, list: List<SearchContent> ->
                hotContent.searchContents.addAll(list)
                return@zip hotContent
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

    fun query(pageNum: Int, k: String): LiveData<ListData<Article>> {

        return WanRepository.getInstance()
            .query(pageNum, k)
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