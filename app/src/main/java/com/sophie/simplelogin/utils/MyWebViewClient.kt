package com.sophie.simplelogin.utils

import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient : WebViewClient() {

    @Suppress("OverridingDeprecatedMember")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        MyLogger.log("User ${SharedPrefs.userName} is opening ${url}")
        try {
            view!!.loadUrl(url)
            return true;
        } catch (e: Exception) {
            MyLogger.logError("WebView interaction error ${e.message}")
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        try {
            MyLogger.log("User ${SharedPrefs.userName} is opening ${request!!.url}")
            view!!.loadUrl(request.url.toString())
            return true;
        } catch (e: Exception) {
            MyLogger.logError("WebView interaction error ${e.message}")
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}