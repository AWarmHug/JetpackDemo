package com.bingo.jetpackdemo.ui.widget.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;


import com.bingo.jetpackdemo.BuildConfig;

import java.lang.reflect.Method;

public class ArmedWebView extends WebView {

    public ArmedWebView(Context context) {
        super(getFixedContext(context));
        initWebView();
    }

    public ArmedWebView(Context context, AttributeSet attrs) {
        super(getFixedContext(context), attrs);
        initWebView();
    }

    public ArmedWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(getFixedContext(context), attrs, defStyleAttr);
        initWebView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ArmedWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(getFixedContext(context), attrs, defStyleAttr, defStyleRes);
        initWebView();
    }

    /**
     * 兼容vivo手机 5.x版本异常奔溃问题
     * Caused by: android.content.res.Resources$NotFoundException: String resource ID #0x2040003
     * https://my.oschina.net/u/1446823/blog/3141787
     * @param context
     * @return
     */
    public static Context getFixedContext(Context context) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) // Android Lollipop 5.0 & 5.1
            return context.createConfigurationContext(new Configuration());
        return context;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView(){
        WebSettings settings = getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setPluginState(WebSettings.PluginState.ON);

        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(true);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            getContext().startActivity(intent);
        });

//        settings.setUserAgentString(settings.getUserAgentString() + "/chebada/" + AppUtils.getVersionName(getContext()) + "/CbdApi/" + AppUtils.getApiVersion(getContext()));
        settings.setAllowContentAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true);
        settings.setSavePassword(false);
        settings.setGeolocationEnabled(true);
        String dir = getContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);

        // 设置H5自动播放音乐
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        } else {
            Class<?> clazz = settings.getClass();
            try {
                Method method = clazz.getDeclaredMethod("setMediaPlaybackRequiresUserGesture", boolean.class);
                method.invoke(settings, false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        CookieManager.getInstance().setAcceptCookie(true);

        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public void loadData(String htmlContent) {
        if (!htmlContent.contains("<html>") && !htmlContent.contains("<head>")) {
            final String htmlHeader = "<html>" +
                    " <head>" +
                    "  <meta charset='utf-8'/>" +
                    "  <meta http-equiv='X-UA-Compatible' content='IE=edge'/>" +
                    "  <meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'/>" +
                    "  <style type=\"text/css\">\n" +
                    "      body {\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "      }\n" +
                    "  </style>" +
                    " </head><body>";
            htmlContent = htmlHeader + htmlContent + "</body></html>";
        }

        loadData(htmlContent, "text/html; charset=UTF-8", null);
    }
}
