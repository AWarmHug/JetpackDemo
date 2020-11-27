package com.bingo.jetpackdemo.ui.core

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.databinding.MainActivityBinding
import com.bingo.jetpackdemo.ui.core.girl.GirlFragment
import com.bingo.jetpackdemo.ui.core.home.HomeFragment
import com.bingo.jetpackdemo.ui.core.gank.TypeFragment
import com.bingo.jetpackdemo.ui.core.mine.MineFragment
import com.bingo.jetpackdemo.ui.core.tree.TreeArticleFragment
import com.bingo.jetpackdemo.ui.core.tree.TreeFragment

class MainActivity : DataBindingAppCompatActivity() {

    private val mBinding: MainActivityBinding by binding(R.layout.main_activity)
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.apply {
            vm = mViewModel
            pager.adapter = FragmentAdapter(this@MainActivity)
            pager.isUserInputEnabled = false
            bottomNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> pager.setCurrentItem(0, false)
                    R.id.type -> pager.setCurrentItem(1, false)
                    R.id.gank -> pager.setCurrentItem(2, false)
                    R.id.girl -> pager.setCurrentItem(3, false)
                    R.id.mine -> pager.setCurrentItem(4, false)
                    else -> pager.setCurrentItem(4, false)
                }
                return@setOnNavigationItemSelectedListener true
            }
        }
    }

    companion object {
        const val TAG = "MainActivity_TAG"
    }
}

private class FragmentAdapter(mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> TreeFragment.newInstance()
            2 -> TypeFragment.newInstance()
            3 -> GirlFragment.newInstance()
            4 -> MineFragment.newInstance()
            else -> MineFragment.newInstance()
        }
    }

}