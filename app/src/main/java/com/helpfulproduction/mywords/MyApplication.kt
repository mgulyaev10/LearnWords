package com.helpfulproduction.mywords

import android.app.Application
import com.helpfulproduction.mywords.core.Words

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContextHolder.context = this
        Words.initAsync(this)
    }

}