package com.bingo.jetpackdemo.ui.core.tree

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.databinding.*
import com.bingo.jetpackdemo.ui.core.articledetail.ArticleDetailActivity

class TreeArticleFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): TreeArticleFragment {
            return TreeArticleFragment()
        }
    }

    private lateinit var binding: TreeArticleFragmentBinding
    private val articleViewModel: TreeArticleViewModel by viewModels()
    private val articleListAdapter: ListAdapter = ListAdapter()

    private var page = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.tree_article_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            list.adapter = articleListAdapter
            list.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.loadingLayout.start()
        articleViewModel.tree().observe(viewLifecycleOwner, Observer {
            it.forEach {
                val rb = LayoutInflater.from(context)
                    .inflate(R.layout.tree_item_view, binding.treeList1, false) as RadioButton
                rb.text = it.name
                rb.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        binding.loadingList.start()
                        binding.treeList2.removeAllViews()
                        it.children.forEach {
                            val rb = LayoutInflater.from(context)
                                .inflate(
                                    R.layout.tree_item_view_2,
                                    binding.treeList2,
                                    false
                                ) as RadioButton
                            rb.text = it.name
                            rb.setOnCheckedChangeListener { buttonView, isChecked ->
                                if (isChecked) {
                                    articleViewModel.article(0, it.id.toString())
                                        .observe(viewLifecycleOwner, Observer {
                                            articleListAdapter.list.clear()
                                            articleListAdapter.list.addAll(it.datas)
                                            articleListAdapter.notifyDataSetChanged()
                                        })
                                }
                            }
                            binding.treeList2.addView(rb)
                        }
                        if (binding.treeList2.childCount != 0) {
                            binding.treeList2.check(binding.treeList2.getChildAt(0).id)
                        }
                        binding.loadingList.dismiss()
                    }
                }
                binding.treeList1.addView(rb)
            }
            if (binding.treeList1.childCount != 0) {
                binding.treeList1.check(binding.treeList1.getChildAt(0).id)
            }
            binding.loadingLayout.dismiss()
        })
    }
}

private class ListAdapter() : RecyclerView.Adapter<ListAdapter.VH>() {

    val list = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<WanTreeItemAdapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.wan_tree_item_adapter_item,
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

    class VH(val binding: WanTreeItemAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }
}
