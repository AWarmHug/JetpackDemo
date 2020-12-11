package com.bingo.jetpackdemo.ui.core.tree.gank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.TypeFragmentBinding
import com.google.android.material.tabs.TabLayout

class TypeFragment : DataBindingFragment() {
    private lateinit var binding: TypeFragmentBinding
    private val viewModel: TypeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.type_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            Category.values().take(2).forEach {
                tabCategory.addTab(tabCategory.newTab().setText(it.title))
            }
            val article = TypeCategoryItemFragment.newInstance(Category.values().take(2)[0])
            val ganhuo = TypeCategoryItemFragment.newInstance(Category.values().take(2)[1])
            childFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContent, article)
                .add(R.id.fragmentContent, ganhuo)
                .hide(ganhuo)
                .commit()
            tabCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.apply {
                        if (position == 0) {
                            childFragmentManager
                                .beginTransaction()
                                .show(article)
                                .hide(ganhuo)
                                .commit()
                        } else {
                            childFragmentManager
                                .beginTransaction()
                                .show(ganhuo)
                                .hide(article)
                                .commit()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    companion object {
        fun newInstance(): TypeFragment {
            return TypeFragment()
        }
    }

}
