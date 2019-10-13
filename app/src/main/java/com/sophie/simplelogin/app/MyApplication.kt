package com.sophie.simplelogin.app

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.sophie.simplelogin.utils.Firebase
import com.sophie.simplelogin.utils.MyLogger
import com.sophie.simplelogin.utils.SharedPrefs


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.create(this)
        MyLogger.create(this)
        Firebase.create(this)
        Firebase.logToFirebase("user ${SharedPrefs.userName} opened the app", FirebaseAnalytics.Event.APP_OPEN)
    }
}