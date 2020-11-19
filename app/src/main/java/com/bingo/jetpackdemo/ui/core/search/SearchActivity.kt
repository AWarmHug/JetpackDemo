package com.bingo.jetpackdemo.ui.core.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.databinding.*

class SearchActivity : DataBindingAppCompatActivity() {
    private val binding: SearchActivityBinding by binding(R.layout.search_activity)
    private val viewViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            scrollHotKey.visibility = View.VISIBLE
            list.visibility = View.VISIBLE
        }

        viewViewModel.hotContent().observe(this@SearchActivity, Observer {
            it.hotKeys.forEach {
//                val keyBinding: SearchActivityPopularKeyItemBinding = DataBindingUtil.inflate(
//                    LayoutInflater.from(this@SearchActivity),
//                    R.layout.search_activity_popular_key_item, binding.linearHot, true
//                )
//                keyBinding.hotKey = it
                val key = HotKeyView(this)
                key.binding.hotKey = it
                binding.linearHot.addView(key)
            }
            it.hotArticles.forEach {
//                val articleBinding: SearchActivityPopularArticleItemBinding =
//                    DataBindingUtil.inflate(
//                        LayoutInflater.from(this@SearchActivity),
//                        R.layout.search_activity_popular_article_item, binding.linearHot, true
//                    )
//                articleBinding.article = it
                val article = HotArticleView(this)
                article.binding.article = it
                binding.linearHot.addView(article)
            }
        })

        binding.search.binding.btnSearch.setOnClickListener {
            viewViewModel.query(0, binding.search.binding.etContent.text.toString())
                .observe(this, Observer {
                    print("skdbjkasndjksan")
                })
        }


    }

}