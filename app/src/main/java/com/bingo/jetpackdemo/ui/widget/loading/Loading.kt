package com.bingo.jetpackdemo.ui.widget.loading

import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.bingo.jetpackdemo.AppException
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.databinding.LoadingLayoutBinding
import com.bingo.jetpackdemo.databinding.LoadingViewBinding

interface Loading {

    fun setState(state: State);

    open class State {
        companion object {
            val start = Start()
            val dismiss = Dismiss()
            fun fail(cause: AppException): Fail {
                return Fail(cause)
            }
        }

        class Start : State()

        class Dismiss : State()

        class Fail(val cause: AppException) : State() {

        }
    }
}

abstract class LoadingV @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), Loading {

    override fun setState(state: Loading.State) {
        when (state) {
            is Loading.State.Start -> {
                start()
            }
            is Loading.State.Dismiss -> {
                dismiss()
            }
            is Loading.State.Fail -> {
                fail(state.cause)
            }
        }
    }

    protected abstract fun start()
    protected abstract fun fail(cause: AppException)
    protected abstract fun dismiss()
}

abstract class LoadingD constructor(
    context: Context
) : Dialog(context), Loading {

    override fun setState(state: Loading.State) {
        when (state) {
            is Loading.State.Start -> {
                start()
            }
            is Loading.State.Dismiss -> {
                dismiss()
            }
            is Loading.State.Fail -> {
                showFail(state.cause)
            }
        }
    }

    abstract fun start()
    abstract fun showFail(cause: AppException)
}

class LoadingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LoadingV(context, attributeSet, defStyleAttr) {
    private val binding = DataBindingUtil.inflate<LoadingViewBinding>(
        LayoutInflater.from(context),
        R.layout.loading_view, this, true
    )

    public override fun start() {
        visibility = VISIBLE
        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.repeatCount = ValueAnimator.INFINITE
        binding.lottie.playAnimation()
        binding.tvMsg.text = "正在加载..."
    }

    public override fun fail(cause: AppException) {
        visibility = VISIBLE
        binding.lottie.setAnimation(R.raw.loading_error)
        binding.lottie.repeatCount = 0
        binding.lottie.playAnimation()
        binding.tvMsg.text = "加载失败！！！"
        binding.root.setOnClickListener {
            cause.block?.let {
                it()
            }
        }
    }

    public override fun dismiss() {
        visibility = GONE
        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.repeatCount = 0
        binding.lottie.pauseAnimation()
        binding.tvMsg.text = "正在加载..."
    }

}

class LoadingViewH @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LoadingV(context, attributeSet, defStyleAttr) {
    private val binding = DataBindingUtil.inflate<LoadingViewBinding>(
        LayoutInflater.from(context),
        R.layout.loading_view_h, this, true
    )

    override fun start() {
        visibility = VISIBLE
        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.repeatCount = ValueAnimator.INFINITE
        binding.lottie.playAnimation()
        binding.tvMsg.text = "正在加载..."
    }

    override fun fail(cause: AppException) {
        visibility = VISIBLE
        binding.lottie.setAnimation(R.raw.loading_error)
        binding.lottie.repeatCount = 1
        binding.lottie.playAnimation()
        binding.tvMsg.text = "加载失败！！！"
        binding.root.setOnClickListener {
            cause.block?.let {
                it()
            }
        }
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
) : LoadingV(context, attributeSet, defStyleAttr), Loading {

    private val binding = DataBindingUtil.inflate<LoadingLayoutBinding>(
        LayoutInflater.from(context),
        R.layout.loading_layout, this, false
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(binding.root)
    }

    public override fun start() {
        binding.loadingView.start()
    }

    public override fun fail(cause: AppException) {
        binding.loadingView.fail(cause)
    }

    public override fun dismiss() {
        binding.loadingView.dismiss()
    }

}

class LoadingDialog(context: Context) : LoadingD(context) {
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

    override fun showFail(cause: AppException) {
        loadingView.fail(cause)
    }

    override fun dismiss() {
        loadingView.dismiss()
        super.dismiss()
    }

}

