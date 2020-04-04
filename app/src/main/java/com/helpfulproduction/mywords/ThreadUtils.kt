package com.helpfulproduction.mywords

import android.os.Looper

object ThreadUtils {
    fun assertNotMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw IllegalThreadStateException("UI Thread!")
        }
    }
}