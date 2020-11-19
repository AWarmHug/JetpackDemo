package com.bingo.jetpackdemo.data.entity.wan

data class WanResponse<T>(val data: T, val errorCode: Int, val errorMsg: String) {
    companion object {
        private const val SUCCESS = 0
        private const val FAIL_NEED_LOGIN = -1001
    }

    val isSuccess: Boolean
        get() {
            return errorCode == SUCCESS
        }

    val needLogin: Boolean
        get() {
            return errorCode == FAIL_NEED_LOGIN
        }
}

data class ListData<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)