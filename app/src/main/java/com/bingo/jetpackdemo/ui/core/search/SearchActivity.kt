package com.bingo.jetpackdemo.ui.core.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.data.dao.AppDataBase
import com.bingo.jetpackdemo.data.dao.SearchContent
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.databinding.*
import com.bingo.jetpackdemo.ui.core.articledetail.ArticleDetailActivity
import com.bingo.jetpackdemo.utils.KeyboardUtils

class SearchActivity : DataBindingAppCompatActivity() {
    private val binding: SearchActivityBinding by binding(R.layout.search_activity)
    private val viewViewModel: SearchViewModel by viewModels()
    private val listAdapter: ListAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            scrollHotKey.visibility = View.VISIBLE
            list.visibility = View.GONE
            list.adapter = listAdapter
            list.layoutManager = LinearLayoutManager(this@SearchActivity)
            search.binding.etContent.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    s?.apply {
                        if (isEmpty()) {
                            binding.list.visibility = View.GONE
                        }
                    }
                }
            })
        }

        viewViewModel.hotContent().observe(this@SearchActivity, Observer {
            if (it.searchContents.isNotEmpty()) {

                it.searchContents.forEach { searchContent ->
                    val text = LayoutInflater.from(this).inflate(
                        R.layout.search_activity_recent_search_view_item,
                        binding.flexRecent,
                        false
                    ) as TextView
                    text.text = searchContent.content
                    binding.flexRecent.addView(text)
                }
            }
            it.hotKeys.forEach { hotKey ->
                val keyBinding: SearchActivityPopularKeyItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(this),
                    R.layout.search_activity_popular_key_item, binding.linearHotKey, false
                )
                keyBinding.hotKey = hotKey
                keyBinding.root.setOnClickListener {
                    binding.search.binding.etContent.setText(hotKey.name)
                    binding.search.binding.btnSearch.performClick()
                }
                binding.linearHotKey.addView(keyBinding.root)
            }
            it.hotArticles.forEach { article ->
                val articleBinding: SearchActivityPopularArticleItemBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(this),
                        R.layout.search_activity_popular_article_item,
                        binding.linearHotArticle,
                        false
                    )
                articleBinding.article = article
                articleBinding.root.setOnClickListener {
                    val intent = Intent(binding.root.context, ArticleDetailActivity::class.java)
                    intent.putExtra(
                        "params",
                        ArticleDetailActivity.Params(article.title, article.link)
                    )
                    binding.root.context.startActivity(intent)
                }
                binding.linearHotArticle.addView(articleBinding.root)
            }
        })

        binding.search.binding.btnSearch.setOnClickListener {
            KeyboardUtils.hideSoftInput(this)
            val searchContent = SearchContent(
                binding.search.binding.etContent.text.toString(),
                System.currentTimeMillis()
            )
            AppDataBase.getInstance().searchContentDao().addSearchContent(searchContent)
            viewViewModel.query(0, binding.search.binding.etContent.text.toString())
                .observe(this, Observer {
                    listAdapter.list.clear()
                    listAdapter.list.addAll(it.datas)
                    listAdapter.notifyDataSetChanged()
                    binding.list.visibility = View.VISIBLE
                })
        }

    }
}

private class ListAdapter() : RecyclerView.Adapter<ListAdapter.VH>() {

    val list = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<WanHomeItemAdapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.wan_home_item_adapter_item,
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.article = list[position]
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, ArticleDetailActivity::class.java)
            intent.putExtra(
                "params",
                ArticleDetailActivity.Params(list[position].title, list[position].link)
            )
            holder.binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(val binding: WanHomeItemAdapterItemBinding) : RecyclerView.ViewHolder(binding.root)
}
