package com.sophie.simplelogin.utils

import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.firebase.analytics.FirebaseAnalytics

class MyWebViewClient : WebViewClient() {

    @Suppress("OverridingDeprecatedMember")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        MyLogger.log("User ${SharedPrefs.userName} is opening ${url}", FirebaseAnalytics.Event.SELECT_CONTENT)
        try {
            view!!.loadUrl(url)
            return true;
        } catch (e: Exception) {
            MyLogger.logError("WebView interaction error ${e.message}", null)
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        try {
            MyLogger.log("User ${SharedPrefs.userName} is opening ${request!!.url}", FirebaseAnalytics.Event.SELECT_CONTENT)
            view!!.loadUrl(request.url.toString())
            return true;
        } catch (e: Exception) {
            MyLogger.logError("WebView interaction error ${e.message}", null)
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}