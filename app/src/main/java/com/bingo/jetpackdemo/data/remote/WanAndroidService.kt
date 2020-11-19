package com.bingo.jetpackdemo.data.remote

import com.bingo.jetpackdemo.data.entity.Result
import com.bingo.jetpackdemo.data.entity.Type
import com.bingo.jetpackdemo.data.entity.wan.*
import okhttp3.Cookie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface WanAndroidService {

    @GET("article/list/{pageNum}/json")
    suspend fun article(
        @Path("pageNum") pageNum: Int,
        @Query("cid") cid: String? = null
    ): WanResponse<ListData<Article>>

    @GET("article/top/json")
    suspend fun articleTop(): WanResponse<List<Article>>

    @GET("banner/json")
    suspend fun banner(): WanResponse<List<Banner>>

    @GET("tree/json")
    suspend fun tree(): WanResponse<List<TreeItem>>

    @GET("project/tree/json")
    suspend fun projectTree(): WanResponse<List<TreeItem>>

    @GET("project/list/{pageNum}/json")
    suspend fun projectList(
        @Path("pageNum") pageNum: Int,
        @Query("cid") cid: String? = null
    ): WanResponse<ListData<Article>>

    @GET("hotkey/json")
    suspend fun hotKey(): WanResponse<List<HotKey>>

    @FormUrlEncoded
    @POST("article/query/{pageNum}/json")
    suspend fun query(
        @Path("pageNum") pageNum: Int,
        @Field("k") k: String
    ): WanResponse<ListData<Article>>

    companion object {
        private const val BASE_URL = "https://www.wanandroid.com/"

        fun create(): WanAndroidService {

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
                .create(WanAndroidService::class.java)
        }
    }
}