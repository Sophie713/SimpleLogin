package com.sophie.simplelogin.utils

import android.content.Context
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.sophie.simplelogin.R


object MyLogger {

    private lateinit var KEY: String;
    fun create(context: Context) {
        KEY = context.resources.getString(R.string.app_name)
    }

    fun log(message: String, firebaseAnalyticsEvent: String?) {
        Log.i(KEY, message)
        if(firebaseAnalyticsEvent != null)
            Firebase.logToFirebase(message, firebaseAnalyticsEvent)
    }

    fun logError(error: String, firebaseAnalyticsEvent: String?) {
        Log.e(KEY, error)
        if(firebaseAnalyticsEvent != null)
            Firebase.logToFirebase(error, firebaseAnalyticsEvent)
    }
}