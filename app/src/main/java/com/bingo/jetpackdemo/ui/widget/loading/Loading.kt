package com.bingo.jetpackdemo.ui.widget.loading

import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.databinding.LoadingLayoutBinding
import com.bingo.jetpackdemo.databinding.LoadingViewBinding

interface Loading {
    fun start()
    fun showFail()
    fun dismiss()
}

class LoadingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), Loading {
    private val binding = DataBindingUtil.inflate<LoadingViewBinding>(
        LayoutInflater.from(context),
        R.layout.loading_view, this, true
    )

    override fun start() {
        visibility = VISIBLE
        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.repeatCount = ValueAnimator.INFINITE
        binding.lottie.playAnimation()
        binding.tvMsg.text = "正在加载..."
    }

    override fun showFail() {
        visibility = VISIBLE
        binding.lottie.setAnimation(R.raw.loading_error)
        binding.lottie.repeatCount = 1
        binding.lottie.playAnimation()
        binding.tvMsg.text = "加载失败！！！"
    }

    override fun dismiss() {
        visibility = GONE
        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.repeatCount = 0
        binding.lottie.pauseAnimation()
        binding.tvMsg.text = "正在加载..."
    }

}

class LoadingLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), Loading {

    private val binding = DataBindingUtil.inflate<LoadingLayoutBinding>(
        LayoutInflater.from(context),
        R.layout.loading_layout, this, true
    )

    override fun start() {
        visibility = VISIBLE
        binding.loadingView.start()
    }

    override fun showFail() {
        visibility = VISIBLE
        binding.loadingView.showFail()
    }

    override fun dismiss() {
        visibility = GONE
        binding.loadingView.dismiss()
    }

}

class LoadingDialog(context: Context) : Dialog(context), Loading {
    val loadingView = LoadingView(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingView.setPadding(50, 50, 50, 50)
        setContentView(loadingView)
    }

    override fun show() {
        super.show()
        start()
    }

    override fun start() {
        loadingView.start()
    }

    override fun showFail() {
        loadingView.showFail()
    }

    override fun dismiss() {
        loadingView.dismiss()
        super.dismiss()
    }

}

