package com.bingo.jetpackdemo.data.remote

import com.bingo.jetpackdemo.data.entity.Banner
import com.bingo.jetpackdemo.data.entity.BaseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface GankService {

    @GET("banners")
    suspend fun banners(): Response<BaseResponse<List<Banner>>>

    companion object {
        private const val BASE_URL = "https://gank.io/api/v2/"

        fun create(): GankService {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
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