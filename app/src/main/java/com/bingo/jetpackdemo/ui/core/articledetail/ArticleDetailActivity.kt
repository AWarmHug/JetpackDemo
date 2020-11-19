package com.bingo.jetpackdemo.ui.core.articledetail

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingAppCompatActivity
import com.bingo.jetpackdemo.databinding.ArticleDetailActivityBinding
import com.bingo.jetpackdemo.ui.widget.web.AppWebChromeClient
import com.bingo.jetpackdemo.ui.widget.web.AppWebViewClient
import kotlinx.android.parcel.Parcelize

class ArticleDetailActivity : DataBindingAppCompatActivity() {

    private val binding: ArticleDetailActivityBinding by binding(R.layout.article_detail_activity)
    private val params: Params? by lazy {
        intent.getParcelableExtra("params")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            params?.apply {
                toolbar.title = title

                val appWebViewClient = AppWebViewClient()
                appWebViewClient.addDelegate(object : AppWebViewClient.WebViewClientDelegate {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        pb.progress = 0
                        pb.visibility = View.VISIBLE
                        loadingLayout.start()
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        pb.progress = 100
                        pb.visibility = View.GONE
                        loadingLayout.dismiss()
                    }


                })

                val appWebChromeClient = AppWebChromeClient()
                appWebChromeClient.addDelegate(object : AppWebChromeClient.WebChromeClientDelegate {
                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        super.onReceivedTitle(view, title)
                        if (TextUtils.isEmpty(toolbar.title) && !TextUtils.isEmpty(title)) {
                            toolbar.title = title
                        }
                    }

                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        pb.progress = newProgress
                    }
                })
                web.webChromeClient = appWebChromeClient
                web.webViewClient = appWebViewClient
                web.loadUrl(link)
            }
        }
    }

    @Parcelize
    class Params(val title: String, val link: String) : Parcelable

}