package com.bingo.jetpackdemo.ui.widget.web

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class AppWebViewClient : WebViewClient() {
    private val delegates = mutableListOf<WebViewClientDelegate>()

    fun addDelegate(delegate: WebViewClientDelegate) {
        delegates.add(delegate)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        delegates.forEach {
            it.onPageStarted(view, url, favicon)
        }

    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        delegates.forEach {
            it.onPageFinished(view, url)
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return request?.let {
            view?.loadUrl(it.url.toString())
            return true
        } ?: return super.shouldOverrideUrlLoading(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return url?.let {
            view?.loadUrl(it)
            return true
        } ?: return super.shouldOverrideUrlLoading(view, url)
    }

    interface WebViewClientDelegate {
        fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {}
        fun onPageFinished(view: WebView?, url: String?) {}
    }

}