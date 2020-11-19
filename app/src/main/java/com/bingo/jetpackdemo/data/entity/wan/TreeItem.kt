package com.bingo.jetpackdemo.data.entity.wan

data class TreeItem(
    val children: List<TreeItem>,
    val courseId: Int,
    val id: Int,
    var name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)