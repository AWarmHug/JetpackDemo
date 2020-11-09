package com.bingo.jetpackdemo

open class AppException(message: String) : Exception(message) {

}

class EmptyException() : AppException("data is empty") {


}