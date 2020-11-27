package com.bingo.jetpackdemo.ui.core.gank

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.databinding.HomeItemAdapterItemBinding
import com.bingo.jetpackdemo.ui.core.articledetail.ArticleDetailActivity

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.VH>() {

    val list = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: HomeItemAdapterItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_item_adapter_item,
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setArticle(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(val binding: HomeItemAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setArticle(article: Article) {
            binding.article = article
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ArticleDetailActivity::class.java)
                intent.putExtra("params", ArticleDetailActivity.Params(article.title, article.url))
                binding.root.context.startActivity(intent)
            }
        }

    }
}