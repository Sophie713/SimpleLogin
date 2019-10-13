package com.sophie.simplelogin.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.sophie.simplelogin.R
import com.sophie.simplelogin.constants.Const
import com.sophie.simplelogin.utils.BaseActivity
import com.sophie.simplelogin.utils.MyLogger
import com.sophie.simplelogin.utils.SharedPrefs

class LoginActivity : BaseActivity() {

    // old sign in variable: var newAccount = SharedPrefs.userName == "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                Toast.makeText(this, "Sorry, we cannot load the website without internet permission.", Toast.LENGTH_LONG).show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), Const.PERMISSIONS_REQUEST_INTERNET)
            }
        } else {
            createSignInIntent()
        }
        /* old login logic
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
        }*/
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == Const.PERMISSIONS_REQUEST_INTERNET) {
            createSignInIntent()
            /* old login
            goToCezWebsite()*/
            MyLogger.log("Internet permission granted ${packageManager.getActivityInfo(this.getComponentName(), 0)}", null)
        } else {
            MyLogger.logError("Internet permission not granted in ${packageManager.getActivityInfo(this.getComponentName(), 0)}", null)
        }
    }
    /* old sign in: login or sign up
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

    }*/

    private fun goToWebViewActivity() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }

    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            Const.RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Const.RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                try {
                    SharedPrefs.userName = FirebaseAuth.getInstance().currentUser!!.email.toString()
                    goToWebViewActivity()
                    MyLogger.log(SharedPrefs.userName.toString(), null)
                } catch (e: Exception) {
                    MyLogger.logError("User login error: ${e.message}", null)
                }
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                if (response == null)
                    MyLogger.logError("User login error null response.", null)
                else {
                    val e = response.getError()?.getErrorCode()
                    MyLogger.logError("User login error: ${e.toString()}", null)
                }
            }
        }
    }


    private fun themeAndLogo() {
        val providers = emptyList<AuthUI.IdpConfig>()

        // [START auth_fui_theme_logo]
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.notification_bg_low) // Set logo drawable
                .setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen) // Set theme
                .build(),
            Const.RC_SIGN_IN)
        // [END auth_fui_theme_logo]
    }
}
