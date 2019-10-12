package com.sophie.simplelogin.utils

import android.util.Log
import com.sophie.simplelogin.R
import com.sophie.simplelogin.app.MyApplication

object Logger {
    private val KEY = MyApplication.getAppContext().getString(R.string.app_name)

    fun log(message: String) {
        Log.i(KEY, message)
    }

    fun logError(error: String) {
        Log.e(KEY, error)
    }
}