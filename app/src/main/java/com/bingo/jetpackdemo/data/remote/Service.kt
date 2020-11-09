package com.bingo.jetpackdemo.data.remote

import com.bingo.jetpackdemo.data.entity.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val category_ARTICLE = "Article"

enum class Category(val title: String, val api: String) {
    ARTICLE("文章", "Article"),
    GANHUO("干货", "GanHuo"),
    GIRL("尤物", "Girl"),
}

interface GankService {

    /**
     * 首页banner轮播
     * https://gank.io/api/v2/banners 请求方式: GET
    注:返回首页banner轮播的数据
     */
    @GET("banners")
    suspend fun banners(): Result<List<Banner>>


    /**
     * 分类 API
     *https://gank.io/api/v2/categories/<category_type> 请求方式: GET
     * <p>注:获取所有分类具体子分类[types]数据
     * category_type 可接受参数 Article | GanHuo | Girl
     * Article: 专题分类、 GanHuo: 干货分类 、 Girl:妹子图
     * 示例:
     * 获取干货所有子分类
     * <a href="https://gank.io/api/v2/categories/GanHuo"</a>
     * 获取文章所有子分类
     * <a https://gank.io/api/v2/categories/Article</a>
     * 获取妹纸所有子分类
     * <a https://gank.io/api/v2/categories/Girl</a>
     * 妹子图类型只有一项: 且为Girl
     */
    @GET("categories/{category}")
    suspend fun categories(@Path("category") category: String): Result<List<Type>>

    /**
     * 分类数据 API
    https://gank.io/api/v2/data/category/<category>/type/<type>/page/<page>/count/<count>
    请求方式: GET
    注:

    category 可接受参数 All(所有分类) | Article | GanHuo | Girl
    type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
    count: [10, 50]
    page: >=1
    示例:

    获取妹子列表
    https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
    获取Android干货列表
    https://gank.io/api/v2/data/category/GanHuo/type/Android/page/1/count/10
     */
    @GET("data/category/{category}/type/{type}/page/{page}/count/10")
    suspend fun data(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int
    ): Result<List<Article>>

    /**
     * 随机 API
    https://gank.io/api/v2/random/category/<category>/type/<type>/count/<count>
    请求方式: GET
    注:

    category 可接受参数 Article | GanHuo | Girl
    type 可接受参数 Android | iOS | Flutter | Girl，即分类API返回的类型数据
    count: [1, 50]
    示例:

    获取干货分类下Android子分类的10个随机文章列表
    https://gank.io/api/v2/random/category/GanHuo/type/Android/count/10
     */
    @GET("random/category/{category}/type/{type}/count/10")
    suspend fun random(
        @Path("category") category: String,
        @Path("type") type: String
    ): Result<List<Article>>

    /**
     * 文章详情 API
    https://gank.io/api/v2/post/<post_id>
    请求方式: GET
    注:

    post_id 可接受参数 文章id[分类数据API返回的_id字段]
    示例:

    获取5e777432b8ea09cade05263f的详情数据
    https://gank.io/api/v2/post/5e777432b8ea09cade05263f
     */
    @GET("post/{post_id}")
    suspend fun post(
        @Path("post_id") post_id: String
    ): Result<ArticleDetail>

    /**
     * 本周最热 API
    https://gank.io/api/v2/hot/<hot_type>/category/<category>/count/<count>
    请求方式: GET
    注:

    hot_type 可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）❌
    category 可接受参数 Article | GanHuo | Girl
    count: [1, 20]
     */
    @GET("hot/{hot_type}/category/{category}/count/10")
    suspend fun hot(
        @Path("hot_type") hot_type: String, @Path("category") category: String
    ): Result<List<Article>>

    /**
     * 文章评论获取 API
    https://gank.io/api/v2/post/comments/<post_id>
    请求方式: GET
    注:

    post_id 可接受参数 文章Id
     */
    @GET("post/comments/<post_id>")
    suspend fun postComments(
        @Path("post_id") post_id: String
    ): Result<List<String>>

    /**
     * 搜索 API
    https://gank.io/api/v2/search/<search>/category/<category>/type/<type>/page/<page>/count/<count>
    请求方式: GET
    注:

    search 可接受参数 要搜索的内容
    category 可接受参数 All[所有分类] | Article | GanHuo
    type 可接受参数 Android | iOS | Flutter ...，即分类API返回的类型数据
    count: [10, 50]
    page: >=1
    示例:

    搜索干货-Android分类下的Android关键字
    https://gank.io/api/v2/search/android/category/GanHuo/type/Android/page/1/count/10
    搜索文章-Android分类下的Android关键字
    https://gank.io/api/v2/search/android/category/Article/type/Android/page/1/count/10
    搜索全部分类下的Android关键字
    https://gank.io/api/v2/search/android/category/All/type/All/page/1/count/10
     */
    @GET("search/{search}/category/{category}/type/{type}/page/{page}/count/10")
    suspend fun search(
        @Path("search") search: String,
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: String,
    ): Result<List<Search>>


    companion object {
        private const val BASE_URL = "https://gank.io/api/v2/"

        fun create(): GankService {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GankService::class.java)
        }
    }

}