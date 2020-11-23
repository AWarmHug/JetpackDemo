package com.bingo.jetpackdemo


open class AppException : RuntimeException {
    var block: (() -> Unit?)? = null
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)

}

class ResultException() : AppException("data is error") {


}

class EmptyException() : AppException("data is empty") {


}