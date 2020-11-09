package com.bingo.jetpackdemo.ui.core.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.entity.Type
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.HomeItemAdapterItemBinding
import com.bingo.jetpackdemo.databinding.TypeItemFragmentBinding

class TypeItemFragment(val type: Type, val category: Category) : DataBindingFragment() {

    private lateinit var binding: TypeItemFragmentBinding
    private val viewModel: TypeItemViewModel by viewModels()
    private val listAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.type_item_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = listAdapter
        binding.list.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.data(category.api, type.type, 0).observe(viewLifecycleOwner, {
            it.data?.apply {
                listAdapter.list.clear()
                listAdapter.list.addAll(this)
                listAdapter.notifyDataSetChanged()
            }
        })
    }

    companion object {
        fun newInstance(type: Type, category: Category): TypeItemFragment {
            return TypeItemFragment(type, category)
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