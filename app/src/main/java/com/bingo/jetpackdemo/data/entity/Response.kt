package com.bingo.jetpackdemo.data.entity

data class BaseResponse<T>(val status: Int, val data: T)

data class Banner(
    val image: String,
    val title: String,
    val url: String
)
