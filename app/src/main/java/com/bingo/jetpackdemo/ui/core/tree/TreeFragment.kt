package com.bingo.jetpackdemo.ui.core.tree

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.databinding.TreeFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class TreeFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): TreeFragment {
            return TreeFragment()
        }
    }

    private lateinit var binding: TreeFragmentBinding

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

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            pager.adapter =
                TreeFragmentVpAdapter(
                    this@TreeFragment
                )
            val tabLayoutMediator = TabLayoutMediator(
                tab, pager, true, false
            ) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "文章"
                    }
                    1 -> {
                        tab.text = "项目"
                    }
                    2 -> {
                        tab.text = "导航"
                    }
                    else -> {
                        tab.text = "文章"
                    }
                }
            }
            tabLayoutMediator.attach()
        }
    }
}

private class TreeFragmentVpAdapter(
    fragment: TreeFragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TreeArticleFragment.newInstance()
            1 -> TreeProjectFragment.newInstance()
            2 -> TreeNavigationFragment.newInstance()
            else -> TreeArticleFragment.newInstance()
        }
    }


}
