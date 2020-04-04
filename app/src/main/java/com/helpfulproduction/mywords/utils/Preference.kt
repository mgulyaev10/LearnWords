package com.helpfulproduction.mywords.utils

import android.content.Context
import com.helpfulproduction.mywords.AppContextHolder

object Preference {
    private const val PREF_WORDS_APP_NAME = "pref_myWords_app"
    private const val KEY_IS_FIRST_LAUNCH = "is_first_launch"
    private const val KEY_IS_DATA_UNPACKED = "is_data_unpacked"

    fun isFirstLaunch(context: Context): Boolean {
        context.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE).let { pref ->
            return pref.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        }
    }

    fun setFirstLaunch(context: Context?) {
        context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)
            ?.edit()
            ?.putBoolean(KEY_IS_FIRST_LAUNCH, false)
            ?.apply()
    }

    fun isDataUnpacked(context: Context): Boolean {
        context.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE).let { pref ->
            return pref.getBoolean(KEY_IS_DATA_UNPACKED, false)
        }
    }

    fun setDataUnpacked(context: Context?, isDataUnpacked: Boolean) {
        context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)
            ?.edit()
            ?.putBoolean(KEY_IS_DATA_UNPACKED, isDataUnpacked)
            ?.apply()
    }

    @Deprecated("DEBUG")
    fun clear() {
        AppContextHolder.context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)
            ?.edit()
            ?.clear()
            ?.apply()
    }

}