package com.helpfulproduction.mywords.utils

import android.util.TypedValue
import com.helpfulproduction.mywords.android.AppContextHolder

object Screen {

    fun dp(dp: Float): Int {
        val resources = AppContextHolder.context!!.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        ).toInt()
    }
}