package com.sophie.simplelogin.utils

import android.content.Context
import android.os.Bundle
import com.google.android.gms.common.logging.Logger
import com.google.firebase.analytics.FirebaseAnalytics

object Firebase {
    var mFirebaseAnalytics: FirebaseAnalytics? = null
    fun create(context: Context) {
        if (mFirebaseAnalytics == null)
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    fun logToFirebase(message: String, firebaseAnalyticsEvent: String?) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, SharedPrefs.userName)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, message)
        try {
            if (firebaseAnalyticsEvent == null)
                Firebase.mFirebaseAnalytics!!.logEvent("UNSPECIFIED_EVENT", bundle)
            else
                Firebase.mFirebaseAnalytics!!.logEvent(firebaseAnalyticsEvent, bundle)
        } catch (e: Exception) {
            MyLogger.logError("firebaseAnalyticsEvent error: ${e.message}", null)
        }
    }
}