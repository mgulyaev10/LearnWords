package com.mvwords.utils

import android.content.Context

object Preference {
    private const val PREF_WORDS_APP_NAME = "pref_mvWords_app"
    private const val KEY_IS_FIRST_LAUNCH = "is_first_launch"

    fun isFirstLaunch(context: Context): Boolean {
        context.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE).let { pref ->
            return pref.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        }
    }

}