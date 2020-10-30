package com.bingo.jetpackdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.data.remote.GankService
import com.bingo.jetpackdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : DataBindingAppCompatActivity() {

    private val mBinding: ActivityMainBinding by binding(R.layout.activity_main)
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.apply {
            vm = mViewModel
            mViewModel.banners().observe(this@MainActivity, {
                Log.d(TAG, "onCreate: ${Thread.currentThread()}")
            })
        }
    }


    companion object {
        const val TAG = "MainActivity_TAG"
    }
}