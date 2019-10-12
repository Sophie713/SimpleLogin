package com.sophie.simplelogin.app

import android.app.Application
import android.content.Context
import com.sophie.simplelogin.utils.SharedPrefs

object MyApplication : Application () {

    fun getAppContext(): Context{
        return this;
    }

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.create(this);
    }

}