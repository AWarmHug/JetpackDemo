package com.bingo.jetpackdemo.ui.core.gank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.jetpackdemo.EmptyException
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.Type
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.TypeItemFragmentBinding

class TypeItemFragment(val type: Type, val category: Category) : DataBindingFragment() {

    private lateinit var binding: TypeItemFragmentBinding
    private val viewModel: TypeItemViewModel by viewModels()
    private val listAdapter = ArticleListAdapter()
    private var page = 0
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
//        binding.list.addItemDecoration(
//            LineItemDecoration(
//                context,
//                LinearLayoutManager.VERTICAL,
//                8.dp,
//                ContextCompat.getColor(binding.list.context, R.color.bg_grey)
//            )
//        )
        binding.swipe.setOnRefreshListener {
            page = 0
            data(page)
        }
        binding.list.itemAnimator = DefaultItemAnimator()
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.setOnLoadMoreListener {
            page += 1
            data(page)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data(page)
    }

    private fun data(page: Int) {
        if (page == 0) {
            binding.loadingLayout.start()
        }

        viewModel.data(category.api, type.type, page).observe(viewLifecycleOwner, {
            it.data?.apply {
                if (isNotEmpty()) {
                    if (page == 0) {
                        listAdapter.list.clear()
                        listAdapter.list.addAll(this)
                        listAdapter.notifyDataSetChanged()
                        binding.loadingLayout.dismiss()
                        binding.swipe.isRefreshing = false
                    } else {
                        listAdapter.list.addAll(this)
                        listAdapter.notifyDataSetChanged()
                    }
                    binding.list.isLoadMoreAble = true
                } else {
                    binding.list.isLoadMoreAble = false
                }
            } ?: apply {
                binding.loadingLayout.fail(EmptyException())
            }
        })
    }

    companion object {
        fun newInstance(type: Type, category: Category): TypeItemFragment {
            return TypeItemFragment(type, category)
        }
    }

}

