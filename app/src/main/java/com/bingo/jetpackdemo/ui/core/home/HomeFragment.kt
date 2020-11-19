package com.bingo.jetpackdemo.ui.core.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.databinding.HomeFragmentBinding
import com.bingo.jetpackdemo.databinding.WanHomeItemAdapterItemBinding
import com.bingo.jetpackdemo.ui.core.articledetail.ArticleDetailActivity
import com.bingo.jetpackdemo.ui.core.search.SearchActivity
import com.bingo.jetpackdemo.ui.widget.banner.ImageAdapter
import com.google.android.material.appbar.AppBarLayout
import com.youth.banner.indicator.CircleIndicator

class HomeFragment() : DataBindingFragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModels()
    private val listAdapter: ListAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.home_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewModel
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.search -> {
                        startActivity(Intent(context, SearchActivity::class.java))
                        return@setOnMenuItemClickListener true
                    }
                    else ->
                        return@setOnMenuItemClickListener true
                }
            }
            list.adapter = listAdapter
            list.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.banners().observe(viewLifecycleOwner, {
            binding.banner.addBannerLifecycleObserver(viewLifecycleOwner)
                .setAdapter(ImageAdapter(it.data))
                .setIndicator(CircleIndicator(context))
        })

        viewModel.article(0).observe(viewLifecycleOwner, {
            listAdapter.list.clear()
            listAdapter.list.addAll(it.datas)
            listAdapter.notifyDataSetChanged()
        })
    }

    companion object {

        private const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            return HomeFragment()
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

    class VH(val binding: WanHomeItemAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }
}

