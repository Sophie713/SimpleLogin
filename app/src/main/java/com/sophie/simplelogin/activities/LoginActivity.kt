package com.sophie.simplelogin.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.sophie.simplelogin.R
import com.sophie.simplelogin.constants.Const
import com.sophie.simplelogin.utils.BaseActivity
import com.sophie.simplelogin.utils.MyLogger
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btn_submit.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.INTERNET
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.INTERNET
                    )
                ) {
                    Toast.makeText(
                        this,
                        "Sorry, we cannot load the website without internet permission.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.INTERNET),
                        Const.PERMISSIONS_REQUEST_INTERNET
                    )
                }
            } else {
                goToCezWebsite()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == Const.PERMISSIONS_REQUEST_INTERNET) {
            goToCezWebsite()
            MyLogger.log("Internet permission granted ${packageManager.getActivityInfo(this.getComponentName(), 0)}")
        } else {
            MyLogger.logError("Internet permission not granted in ${packageManager.getActivityInfo(this.getComponentName(), 0)}")
        }
    }

    fun goToCezWebsite() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }
}
