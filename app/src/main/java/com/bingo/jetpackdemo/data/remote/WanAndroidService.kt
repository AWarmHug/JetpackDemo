package com.bingo.jetpackdemo.data.remote

import okhttp3.Cookie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface WanAndroidService{




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