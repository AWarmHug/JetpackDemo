package com.bingo.jetpackdemo.ui.core.tree

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bingo.jetpackdemo.data.WanRepository
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.data.entity.wan.ListData
import com.bingo.jetpackdemo.data.entity.wan.TreeItem
import com.bingo.jetpackdemo.data.entity.wan.WanResponse
import kotlinx.coroutines.flow.catch

class TreeArticleViewModel : ViewModel() {

    fun tree(): LiveData<List<TreeItem>> {
        return WanRepository.getInstance()
            .tree()
            .catch {

            }
            .asLiveData()
    }

    fun article(pageNum: Int, cid: String): LiveData<ListData<Article>> =
        WanRepository.getInstance()
            .article(pageNum, cid)
            .catch { cause ->
                Log.d("**********", "article: $cause")
            }
            .asLiveData()

}