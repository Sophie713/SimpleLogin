package com.sophie.simplelogin.utils

import android.content.Context
import android.util.Log
import com.sophie.simplelogin.R

object MyLogger {

    private var logger: MyLogger? = null;
    private lateinit var KEY: String;

    fun create(context: Context) {
        KEY = context.resources.getString(R.string.app_name)
    }

    fun log(message: String) {
        Log.i(KEY, message)
    }

    fun logError(error: String) {
        Log.e(KEY, error)
    }
}