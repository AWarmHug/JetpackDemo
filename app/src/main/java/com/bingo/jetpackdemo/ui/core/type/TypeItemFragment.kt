package com.bingo.jetpackdemo.ui.core.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.Type
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.TypeItemFragmentBinding
import com.bingo.jetpackdemo.ui.core.ArticleListAdapter
import com.bingo.jetpackdemo.ui.widget.decoration.LineItemDecoration
import com.chebada.utils.ktx.dp

class TypeItemFragment(val type: Type, val category: Category) : DataBindingFragment() {

    private lateinit var binding: TypeItemFragmentBinding
    private val viewModel: TypeItemViewModel by viewModels()
    private val listAdapter = ArticleListAdapter()

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
        binding.list.itemAnimator = DefaultItemAnimator()
        binding.list.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.loadingLayout.start()
        viewModel.data(category.api, type.type, 0).observe(viewLifecycleOwner, {
            it.data?.apply {
                listAdapter.list.clear()
                listAdapter.list.addAll(this)
                listAdapter.notifyItemRangeInserted(0, size)
                binding.loadingLayout.dismiss()
            }
        })
    }

    companion object {
        fun newInstance(type: Type, category: Category): TypeItemFragment {
            return TypeItemFragment(type, category)
        }
    }

}

