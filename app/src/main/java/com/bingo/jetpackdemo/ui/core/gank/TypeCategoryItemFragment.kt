package com.bingo.jetpackdemo.ui.core.gank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.Type
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.TypeCategoryItemFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class TypeCategoryItemFragment() : DataBindingFragment() {

    companion object {
        fun newInstance(category: Category): TypeCategoryItemFragment {
            val typeCategoryItemFragment = TypeCategoryItemFragment()
            val bundle = Bundle()
            bundle.putSerializable("category", category)
            typeCategoryItemFragment.arguments = bundle
            return typeCategoryItemFragment
        }
    }

    private lateinit var binding: TypeCategoryItemFragmentBinding
    private val viewModel: TypeViewModel by viewModels()

    private val category: Category by lazy {
        arguments?.getSerializable("category") as Category
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.type_category_item_fragment, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            viewModel.categories(category.api).observe(viewLifecycleOwner, {
                it.data?.apply {
                    pager.adapter =
                        TypeCategoryItemFragmentAdapter(
                            this@TypeCategoryItemFragment,
                            it.data,
                            category
                        )
                    val tabLayoutMediator = TabLayoutMediator(
                        tabType, pager, true, false
                    ) { tab, position ->
                        tab.text = it.data[position].title
                    }
                    tabLayoutMediator.attach()
                }
            })
        }
    }
}


private class TypeCategoryItemFragmentAdapter(
    fragment: TypeCategoryItemFragment,
    val types: List<Type>,
    val category: Category
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return types.size
    }

    override fun createFragment(position: Int): Fragment {
        return TypeItemFragment.newInstance(
            types[position],
            category
        )
    }

}