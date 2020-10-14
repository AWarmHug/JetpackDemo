package com.bingo.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.data.remote.GankService
import com.bingo.jetpackdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : DataBindingAppCompatActivity() {

    private val mBinding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.apply {
            Log.d(TAG, "onCreate: ${Thread.currentThread()}")
            lifecycleScope.launch {
                Log.d(TAG, "onCreate: ${Thread.currentThread()}")
                val response = GankService.create().banners();
                if (response.isSuccessful) {
                    val baseResponse = response.body()
                    Log.d(TAG, "onCreate: ${Thread.currentThread()}")

                    Log.d(TAG, "onCreate: ${baseResponse!!.status}")
                } else {

                }
            }

        }
    }


    companion object {
        const val TAG = "MainActivity_TAG"
    }
}