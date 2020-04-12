package com.helpfulproduction.mywords.android

import android.content.Context
import android.widget.Toast
import com.helpfulproduction.mywords.R

object ToastUtils {
    fun showLoadingToast(context: Context?) {
        Toast.makeText(context, R.string.loading_toast, Toast.LENGTH_LONG)
            .show()
    }

    fun showErrorToast(context: Context?) {
        Toast.makeText(context, R.string.error, Toast.LENGTH_LONG)
            .show()
    }
}