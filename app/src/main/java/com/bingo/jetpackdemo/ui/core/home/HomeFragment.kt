package com.bingo.jetpackdemo.ui.core.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.databinding.HomeFragmentBinding
import com.bingo.jetpackdemo.databinding.HomeItemAdapterItemBinding
import com.bingo.jetpackdemo.ui.imageloader.GlideImageLoader
import com.chebada.utils.ktx.dp
import com.chebada.utils.ktx.widthPixels
import com.warm.flowlayout.FlowLayout

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
            list.adapter = listAdapter
            list.layoutManager = LinearLayoutManager(context)

            viewModel.banners().observe(viewLifecycleOwner, {
                Log.d(TAG, "onViewCreated: $it")
            })
            viewModel.random().observe(viewLifecycleOwner, {
                listAdapter.list.clear()
                listAdapter.list.addAll(it)
                listAdapter.notifyDataSetChanged()
            })

        }
    }

    companion object {

        private const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}

private class ListAdapter : RecyclerView.Adapter<ListAdapter.VH>() {

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
        }

    }
}

