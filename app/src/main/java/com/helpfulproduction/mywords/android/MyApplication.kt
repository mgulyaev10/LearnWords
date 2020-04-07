package com.helpfulproduction.mywords.android

import android.app.Application
import com.helpfulproduction.mywords.core.Words
import com.helpfulproduction.mywords.android.AppContextHolder

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContextHolder.context = this
        Words.initAsync(this)
    }

}