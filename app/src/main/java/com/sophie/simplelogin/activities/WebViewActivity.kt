package com.sophie.simplelogin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sophie.simplelogin.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        activity_web_view_wv_cez.loadUrl("https://www.cez.cz/")
    }
}
