package com.sophie.simplelogin.activities

import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.sophie.simplelogin.R
import com.sophie.simplelogin.utils.BaseActivity
import com.sophie.simplelogin.utils.MyLogger
import com.sophie.simplelogin.utils.MyWebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        try {
            webView = activity_web_view_wv_cez
            webView.webViewClient = MyWebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("https://www.cez.cz/")
        } catch (e: Exception) {
            MyLogger.logError("Load WebView error in ${packageManager.getActivityInfo(this.getComponentName(), 0)}")
            Toast.makeText(this, "There has been some unexpected error.", Toast.LENGTH_LONG).show()
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
    }
}
