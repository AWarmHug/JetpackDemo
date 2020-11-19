package com.bingo.jetpackdemo.ui.core.tree

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

class TreeProjectFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): TreeProjectFragment {
            return TreeProjectFragment()
        }
    }

    private lateinit var binding: TreeProjectFragmentBinding
    private val projectViewModel: TreeProjectViewModel by viewModels()
    private val projectListAdapter: ProjectListAdapter = ProjectListAdapter()

    private var page = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.tree_project_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            list.adapter = projectListAdapter
            list.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        projectViewModel.projectList.observe(viewLifecycleOwner, {
            projectListAdapter.list.clear()
            projectListAdapter.list.addAll(it.datas)
            projectListAdapter.notifyDataSetChanged()
            binding.loadingList.dismiss()
        })
    }
}

private class ProjectListAdapter() : RecyclerView.Adapter<ProjectListAdapter.VH>() {

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
