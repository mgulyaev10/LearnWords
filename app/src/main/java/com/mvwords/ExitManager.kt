package com.mvwords

import android.os.Handler
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

object ExitManager {

    private const val DELAY_EXIT_RESET_MS = 1000L
    var canExit = false

    fun tryExit(activity: FragmentActivity?) {
        if (canExit) {
            activity?.finish()
        } else {
            Toast.makeText(activity, R.string.exit_explanation, Toast.LENGTH_SHORT).apply {
                view.findViewById<TextView>(android.R.id.message)?.let { msg ->
                    msg.gravity = Gravity.CENTER
                }
                show()
            }
            canExit = true
            resetExitDelayed()
        }
    }

    private fun resetExitDelayed() {
        Handler().postDelayed({
            canExit = false
        }, DELAY_EXIT_RESET_MS)
    }

}