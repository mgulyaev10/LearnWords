package com.mvwords

import android.content.Context

object Preference {
    private const val PREF_WORDS_APP_NAME = "pref_mvWords_app"
    private const val KEY_NATIVE_LANGUAGE = "key_native_language"
    private const val KEY_STUDIED_LANGUAGE = "key_studied_language"

    fun isFirstLaunch(context: Context): Boolean {
        context.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE).let { pref ->
            return pref.getInt(KEY_NATIVE_LANGUAGE, Language.DEFAULT) == Language.DEFAULT
                    || pref.getInt(KEY_STUDIED_LANGUAGE, Language.DEFAULT) == Language.DEFAULT
        }
    }

    fun setLanguage(context: Context?, language: Language, isNative: Boolean) {
        val key = if (isNative) {
            KEY_NATIVE_LANGUAGE
        } else {
            KEY_STUDIED_LANGUAGE
        }
        context?.getSharedPreferences(PREF_WORDS_APP_NAME, Context.MODE_PRIVATE)?.edit()
            ?.putInt(key, language.id)
            ?.apply()
    }

}