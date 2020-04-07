package com.helpfulproduction.mywords.android

import android.os.Handler
import android.os.Looper

object ThreadUtils {
    fun assertNotMainThread() {
        if (isMainThread()) {
            throw IllegalThreadStateException("UI Thread!")
        }
    }

    fun assertMainThread() {
        if (!isMainThread()) {
            throw IllegalThreadStateException("This is not UI Thread!")
        }
    }

    fun postOnMainThread(action: () -> Unit) {
        Handler(Looper.getMainLooper())
            .post(action)
    }

    private fun isMainThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }
}