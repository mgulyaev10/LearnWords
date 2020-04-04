package com.helpfulproduction.mywords

import android.app.Application

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContextHolder.context = this
    }

}