package com.bingo.jetpackdemo

open class AppException(message: String) : Exception(message) {

}

class ResultException() : AppException("data is error") {


}

class EmptyException() : AppException("data is empty") {


}