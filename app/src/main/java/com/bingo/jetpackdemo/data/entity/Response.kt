package com.bingo.jetpackdemo.data.entity

data class Result<T>(val status: Int, val msg: String?, val data: T?, val counts: Int) {
    companion object {
        private const val SUCCESS = 100
    }

    val isSuccess: Boolean
        get() {
            return status == SUCCESS
        }

}

data class Banner(
    val image: String,
    val title: String,
    val url: String
)

data class Type(
    val _id: String,
    val coverImageUrl: String,
    val desc: String,
    val title: String,
    val type: String
)

data class Article(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)

data class ArticleDetail(
    val _id: String,
    val author: String,
    val category: String,
    val content: String,
    val createdAt: String,
    val desc: String,
    val email: String,
    val images: List<String>,
    val index: Int,
    val isOriginal: Boolean,
    val license: String,
    val likeCounts: Int,
    val likes: List<Any>,
    val markdown: String,
    val originalAuthor: String,
    val publishedAt: String,
    val stars: Int,
    val status: Int,
    val tags: List<String>,
    val title: String,
    val type: String,
    val updatedAt: String,
    val url: String,
    val views: Int
)

data class Comment(
    val _id: String,
    val comment: String,
    val createdAt: String,
    val headUrl: String,
    val postId: String,
    val secondParentId: String,
    val secondParentName: Any,
    val ups: Int,
    val userId: String,
    val userName: String
)

data class Search(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCount: Int,
    val likeCounts: Int,
    val markdown: String,
    val publishedAt: String,
    val stars: Int,
    val status: Int,
    val title: String,
    val type: String,
    val updatedAt: String,
    val url: String,
    val views: Int
)
