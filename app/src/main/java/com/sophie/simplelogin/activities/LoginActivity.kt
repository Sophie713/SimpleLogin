package com.sophie.simplelogin.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sophie.simplelogin.R
import com.sophie.simplelogin.app.MyApplication
import com.sophie.simplelogin.utils.SharedPrefs
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btn_submit.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        SharedPrefs.userName = "user one";
    }
}
