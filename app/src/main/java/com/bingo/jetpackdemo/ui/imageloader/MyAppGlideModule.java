package com.bingo.jetpackdemo.ui.imageloader;

import android.content.Context;

import com.bingo.jetpackdemo.R;
import com.bingo.jetpackdemo.utils.FileUtils;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * 作者: 51hs_android
 * 时间: 2017/6/7
 * 简介:
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    private static final String TAG = "MyAppGlideModule";
    int diskSize = 1024 * 1024 * 100;
    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存

//    @Override
//    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
//        super.registerComponents(context, glide, registry);
//        registry.replace(GlideUrl.class,InputStream.class,new OkHttpUrlLoader.Factory(RetrofitHelper.provideOkHttpClient()));
//
//    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        //重新设置内存限制
        // 自定义内存和图片池大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();

        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (0.8 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (0.8 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
        File file = FileUtils.createFileDir(FileUtils.CACHE_IMAGE);
        if (file != null) {
            DiskCache.Factory diskCacheFactory = new DiskLruCacheFactory(file.getAbsolutePath(), diskSize);
            builder.setDiskCache(diskCacheFactory);
        }

        RequestOptions options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.bg_image_holder)
                .error(R.drawable.bg_image_error)
                .encodeQuality(50)
                .format(DecodeFormat.PREFER_RGB_565);

        builder.setDefaultRequestOptions(options);
    }
}
