package com.sophie.simplelogin.app

import android.app.Application
import com.sophie.simplelogin.utils.MyLogger
import com.sophie.simplelogin.utils.SharedPrefs

class MyApplication : Application () {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.create(this)
        MyLogger.create(this)
    }
}