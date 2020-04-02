package com.mvwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Preference.isFirstLaunch(this)) {
            openChooseLanguageFragment()
        } else {
            openCardFragment()
        }
    }

    private fun openChooseLanguageFragment() {
        val fragment = ChooseLanguageFragment.Builder(isNativeLanguage = true)
            .build()
        val tag = ChooseLanguageFragment.TAG_NATIVE_LANG_SCREEN
        Navigator.go(this, fragment, tag)
    }

    private fun openCardFragment() {
        val fragment = CardWordsFragment.Builder(isNew = false)
            .build()
        val tag = CardWordsFragment.TAG
        Navigator.go(this, fragment, tag)
    }

}
