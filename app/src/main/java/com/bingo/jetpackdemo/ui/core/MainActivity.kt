package com.bingo.jetpackdemo.ui.core

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.databinding.MainActivityBinding
import com.bingo.jetpackdemo.ui.core.favorites.FavoritesFragment
import com.bingo.jetpackdemo.ui.core.home.HomeFragment
import com.bingo.jetpackdemo.ui.core.type.TypeFragment

class MainActivity : DataBindingAppCompatActivity() {

    private val mBinding: MainActivityBinding by binding(R.layout.main_activity)
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.apply {
            vm = mViewModel
            pager.adapter = FragmentAdapter(this@MainActivity)
            pager.setUserInputEnabled(false)
            bottomNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> pager.setCurrentItem(0, false)
                    R.id.type -> pager.setCurrentItem(1, false)
                    R.id.favorites -> pager.setCurrentItem(2, false)
                    else -> pager.setCurrentItem(2, false)
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
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> TypeFragment.newInstance()
            2 -> FavoritesFragment.newInstance()
            else -> FavoritesFragment.newInstance()
        }
    }

}