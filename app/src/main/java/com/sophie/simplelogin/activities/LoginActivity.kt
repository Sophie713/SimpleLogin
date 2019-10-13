package com.sophie.simplelogin.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.sophie.simplelogin.R
import com.sophie.simplelogin.constants.Const
import com.sophie.simplelogin.utils.BaseActivity
import com.sophie.simplelogin.utils.Firebase
import com.sophie.simplelogin.utils.MyLogger
import com.sophie.simplelogin.utils.SharedPrefs
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity() {

    var newAccount = SharedPrefs.userName == "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (newAccount) {
            activity_main_tv_no_account.text = resources.getString(R.string.no_account_yet);
        } else {
            activity_main_tv_no_account.text = resources.getString(R.string.enter_credentials);
        }

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
            MyLogger.log("Internet permission granted ${packageManager.getActivityInfo(this.getComponentName(), 0)}", null)
        } else {
            MyLogger.logError("Internet permission not granted in ${packageManager.getActivityInfo(this.getComponentName(), 0)}", null)
        }
    }

    fun goToCezWebsite() {
        //get nickname and password
        val nickname = activity_main_et_nick_name.text.toString()
        val password = activity_main_et_pass.text.toString()

        if (newAccount) {
            var nameOk = false
            var passwordOk = false
            //check nickname
            if (nickname.length < 21 && nickname.length > 0)
                try {
                    SharedPrefs.userName = nickname
                    nameOk = true
                } catch (e: Exception) {
                    MyLogger.logError("Save nickname error. name: ${nickname} error: ${e.message}", null)
                    Toast.makeText(this, "Please enter a valid nickname.", Toast.LENGTH_SHORT).show()
                    nameOk = false
                }
            //check password
            if (password.length < 21 && password.length > 0)
                try {
                    SharedPrefs.password = password
                    passwordOk = true
                } catch (e: Exception) {
                    MyLogger.logError("Save password error. name: ${password} error: ${e.message}", null)
                    Toast.makeText(this, "Please enter a valid password.", Toast.LENGTH_SHORT).show()
                    passwordOk = false
                }
            if (nameOk && passwordOk) {
                Toast.makeText(this, "Your credentials were saved.", Toast.LENGTH_SHORT).show()
                Firebase.logToFirebase("new user ${SharedPrefs.userName} created an account", FirebaseAnalytics.Event.SIGN_UP)
                goToWebViewActivity()
            }
        } else {
            if (SharedPrefs.userName == nickname && SharedPrefs.password == password) {
                Firebase.logToFirebase("user ${SharedPrefs.userName} logged in", FirebaseAnalytics.Event.LOGIN)
                goToWebViewActivity()
            } else
                Toast.makeText(this, "Your credentials are incorrect. Please, try again.", Toast.LENGTH_SHORT).show()
            //todo optional send email here??
        }

    }

    private fun goToWebViewActivity() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }
}
