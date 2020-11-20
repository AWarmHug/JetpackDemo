package com.bingo.jetpackdemo.ui.core.search

import androidx.lifecycle.*
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.dao.AppDataBase
import com.bingo.jetpackdemo.data.dao.SearchContent
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.ListData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    fun hotContent(): LiveData<HotContent> {
        val recentFlow = AppDataBase.getInstance().searchContentDao().getAllSearchContent()
        val articleFlow = WanRepository.getInstance()
            .articleTop()
        val hotKeyFlow = WanRepository.getInstance()
            .hotKey()

        return hotKeyFlow.zip(articleFlow) { hotKey, article ->
            println("%Tdsnsdndf")
            return@zip HotContent(hotKey, article)
        }.zip(recentFlow) { hotContent: HotContent, list: List<SearchContent> ->
            hotContent.searchContents.addAll(list)
            return@zip hotContent
        }
            .catch { cause ->

            }
            .asLiveData()
    }

    fun query(pageNum: Int, k: String): LiveData<ListData<Article>> {

        return WanRepository.getInstance()
            .query(pageNum, k)
            .catch { cause ->

            }
            .asLiveData()
    }


}