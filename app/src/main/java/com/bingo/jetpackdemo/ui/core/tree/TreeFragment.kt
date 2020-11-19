package com.bingo.jetpackdemo.ui.core.tree

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.TreeFragmentBinding
import com.bingo.jetpackdemo.databinding.TypeFragmentBinding
import com.google.android.material.tabs.TabLayout

class TreeFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): TreeFragment {
            return TreeFragment()
        }
    }

    private lateinit var binding: TreeFragmentBinding
    private var treeProjectPopup: TreeProjectPopup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.tree_fragment, container)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        treeProjectPopup = TreeProjectPopup(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            tab.addTab(tab.newTab().setText("文章"))
            tab.addTab(tab.newTab().setText("项目"))
            val article = TreeArticleFragment.newInstance()
            val project = TreeProjectFragment.newInstance()
            childFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContent, article)
                .add(R.id.fragmentContent, project)
                .hide(project)
                .commit()
            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.apply {
                        if (position == 0) {
                            childFragmentManager
                                .beginTransaction()
                                .setCustomAnimations(
                                    R.animator.card_flip_right_in,
                                    R.animator.card_flip_left_out,
                                    R.animator.card_flip_left_in,
                                    R.animator.card_flip_right_out
                                )
                                .show(article)
                                .hide(project)
                                .commit()
                        } else {
                            childFragmentManager
                                .beginTransaction()
                                .setCustomAnimations(
                                    R.animator.card_flip_right_in,
                                    R.animator.card_flip_left_out,
                                    R.animator.card_flip_left_in,
                                    R.animator.card_flip_right_out
                                )
                                .show(project)
                                .hide(article)
                                .commit()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    treeProjectPopup?.apply {
                        showAsDropDown(binding.tab)
                    }
                }
            })
        }
    }

}
