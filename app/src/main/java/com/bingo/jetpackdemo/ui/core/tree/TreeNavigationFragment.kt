package com.bingo.jetpackdemo.ui.core.tree

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.wan.Article
import com.bingo.jetpackdemo.databinding.TreeNavigationFragmentBinding
import com.bingo.jetpackdemo.databinding.WanTreeNavigationAdapterItemBinding
import com.bingo.jetpackdemo.ui.core.articledetail.ArticleDetailActivity
import com.bingo.jetpackdemo.ui.widget.recyclerview.decoration.LineItemDecoration
import com.chebada.utils.ktx.dp

class TreeNavigationFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): TreeNavigationFragment {
            return TreeNavigationFragment()
        }
    }

    private lateinit var binding: TreeNavigationFragmentBinding
    private val navigationViewModel: TreeNavigationViewModel by viewModels()
    private val navigationListAdapter: NavigationListAdapter = NavigationListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.tree_navigation_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = navigationListAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigationViewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.loadingLayout.setState(it)
        })
        navigationViewModel.navi().observe(viewLifecycleOwner, Observer {
            it.forEach {
                val rb = LayoutInflater.from(context)
                    .inflate(R.layout.tree_item_view, binding.listTitle, false) as RadioButton
                rb.text = it.name
                rb.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        navigationListAdapter.list.clear()
                        navigationListAdapter.list.addAll(it.articles)
                        navigationListAdapter.notifyDataSetChanged()
                    }
                }
                binding.listTitle.addView(rb)
            }
            if (binding.listTitle.childCount != 0) {
                binding.listTitle.check(binding.listTitle.getChildAt(0).id)
            }
        })
    }


    override fun getTitle(): String {
        return "导航"
    }
}

private class NavigationListAdapter() : RecyclerView.Adapter<NavigationListAdapter.VH>() {

    val list = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<WanTreeNavigationAdapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.wan_tree_navigation_adapter_item,
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

    class VH(val binding: WanTreeNavigationAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}