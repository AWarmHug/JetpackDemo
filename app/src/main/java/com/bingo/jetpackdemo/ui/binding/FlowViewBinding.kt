package com.bingo.jetpackdemo.ui.binding

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bingo.jetpackdemo.ui.imageloader.GlideImageLoader
import com.bingo.jetpackdemo.ui.widget.loading.LoadingLayout
import com.chebada.utils.ktx.dp
import com.chebada.utils.ktx.widthPixels
import com.warm.flowlayout.FlowLayout

@BindingAdapter("bindingNineImageView")
fun bindingNineImageView(flow: FlowLayout, imagePaths: List<String>) {
    if (imagePaths.isNotEmpty()) {
        val horizontalSize: Int = getHorizontalSize(imagePaths)
        reSetFlowLayout(flow, horizontalSize)
        flow.horizontalSize = horizontalSize
        if (flow.childCount != 0) {
            flow.removeAllViews()
        }
        for (i in imagePaths.indices) {
            val imageView: ImageView =
                createImageView(flow.context, imagePaths, i, horizontalSize)
            flow.addView(imageView)
        }
        flow.visibility = View.VISIBLE
    } else {
        flow.visibility = View.GONE
    }
}

private fun getIVHeight(context: Context, horizontalSize: Int): Int {
    return (context.widthPixels - (48 + 42).dp - (horizontalSize - 1) * (8).dp) / horizontalSize
}

private fun reSetFlowLayout(flowLayout: FlowLayout, horizontalSize: Int) {
    val lp = flowLayout.layoutParams
    if (horizontalSize == 1 || horizontalSize == 3) {
        //MATCH_PARENT
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT
    } else {
        //只需要2/3
        lp.width = getIVHeight(flowLayout.context, 3) * 2 + (8).dp
    }
//        flowLayout.setLayoutParams(lp);
}

private fun getHorizontalSize(strings: List<String>): Int {
    return if (strings.size == 1) {
        1
    } else if (strings.size == 2 || strings.size == 4) {
        2
    } else {
        3
    }
}

private fun createImageView(
    context: Context,
    imagePaths: List<String>,
    position: Int,
    horizontalSize: Int
): ImageView {
    val imageView = ImageView(context)
    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
    val height: Int = if (horizontalSize == 1) {
        (context.widthPixels - (24).dp) * 2 / 3
    } else {
        //2或3的情况都按照3来处理
        getIVHeight(context, 3)
    }
    imageView.layoutParams =
        ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height)
    GlideImageLoader.loadImage(imageView.context, imageView, imagePaths[position])
    imageView.setOnClickListener { v: View? ->
        //TODO 点击查看图片
    }
    return imageView
}

@BindingAdapter("url")
fun bindingUrl(imageView: ImageView, url: String) {
    GlideImageLoader.loadImage(imageView.context, imageView, url)
}
