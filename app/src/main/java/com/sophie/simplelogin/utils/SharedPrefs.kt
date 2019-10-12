package com.sophie.simplelogin.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefs {
    private val PREFERENCE_NAME = "com.sophie.simplelogin.prefs";
    private val MODE = Context.MODE_PRIVATE;
    private lateinit var prefs: SharedPreferences;

    fun create(context: Context) {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE)
    }

    //shared preferences values
    var userName: String
        get() = prefs.getString("userName", "")
        set(value) = prefs.edit().putString("userName", value).apply()

    var password: String
        get() = prefs.getString("password", "")
        set(value) = prefs.edit().putString("password", value).apply()


}