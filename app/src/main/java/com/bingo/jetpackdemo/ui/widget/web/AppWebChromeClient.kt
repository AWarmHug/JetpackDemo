package com.bingo.jetpackdemo.ui.widget.web

import android.webkit.WebChromeClient
import android.webkit.WebView

class AppWebChromeClient : WebChromeClient() {
    private val delegates = mutableListOf<WebChromeClientDelegate>()

    fun addDelegate(delegate: WebChromeClientDelegate) {
        delegates.add(delegate)
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        delegates.forEach {
            it.onReceivedTitle(view, title)
        }
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        delegates.forEach {
            it.onProgressChanged(view, newProgress)
        }
    }

    interface WebChromeClientDelegate {
        fun onReceivedTitle(view: WebView?, title: String?) {}
        fun onProgressChanged(view: WebView?, newProgress: Int) {}
    }

}