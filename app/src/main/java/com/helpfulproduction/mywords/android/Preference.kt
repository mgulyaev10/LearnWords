package com.helpfulproduction.mywords.android

import android.content.Context
import androidx.annotation.StringRes
import com.helpfulproduction.mywords.R

object Preference {
    private const val PREF_WORDS_APP_NAME = "pref_myWords_app"
    private const val PREF_SETTINGS_NAME = "pref_settings"
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

    fun isDataUnpacked(context: Context?): Boolean {
        context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)?.let { pref ->
            return pref.getBoolean(KEY_IS_DATA_UNPACKED, false)
        }
        return false
    }

    fun setDataUnpacked(context: Context?, isDataUnpacked: Boolean) {
        context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)
            ?.edit()
            ?.putBoolean(KEY_IS_DATA_UNPACKED, isDataUnpacked)
            ?.apply()
    }

    fun isBadDevice(context: Context): Boolean = getSettings(context, R.string.key_bad_device)

    fun getSettings(context: Context, @StringRes keyRes: Int): Boolean {
        val key = context.getString(keyRes)
        return context.getSharedPreferences(PREF_SETTINGS_NAME, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    fun setSettings(context: Context, @StringRes keyRes: Int, value: Boolean) {
        val key = context.getString(keyRes)
        context.getSharedPreferences(PREF_SETTINGS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    @Deprecated("DEBUG")
    fun clear() {
        AppContextHolder.context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)
            ?.edit()
            ?.clear()
            ?.apply()
    }

}