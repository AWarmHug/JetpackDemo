package com.bingo.jetpackdemo.ui.widget.banner

import com.bingo.jetpackdemo.data.entity.wan.Banner
import com.bingo.jetpackdemo.ui.imageloader.GlideImageLoader
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class ImageAdapter(val list: List<Banner>) : BannerImageAdapter<Banner>(list) {
    override fun onBindView(holder: BannerImageHolder?, data: Banner?, position: Int, size: Int) {
        GlideImageLoader.loadImage(holder!!.itemView.context, holder.imageView, data!!.imagePath)
    }

}