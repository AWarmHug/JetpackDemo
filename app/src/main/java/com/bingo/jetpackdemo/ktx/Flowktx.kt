package com.bingo.jetpackdemo.ktx

import androidx.lifecycle.MutableLiveData
import com.bingo.jetpackdemo.ui.widget.loading.Loading
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
