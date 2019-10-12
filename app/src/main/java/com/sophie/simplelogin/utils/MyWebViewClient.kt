package com.sophie.simplelogin.utils

import android.webkit.WebView
import android.webkit.WebViewClient
import java.util.logging.Logger

class MyWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        MyLogger.log("User ${SharedPrefs.userName} is opening ${url}")
        try {
            view!!.loadUrl(url)
            return true;
        } catch (e: Exception) {
            MyLogger.logError("WebView interaction error ${e.message}")
            super.shouldOverrideUrlLoading(view, url)
            return false
        }
    }
}