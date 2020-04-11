package com.helpfulproduction.mywords

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.helpfulproduction.mywords.android.Preference

object AnimationHelper {

    fun swipeCardAnimation(context: Context?,
                           view: View,
                           animationListener: CardAnimationListener) {
        if (context == null) {
            return
        }
        if (Preference.isBadDevice(context)) {
            animationListener.actionAfter.invoke()
            return
        }
        val attr = if (animationListener.isLeft) {
            R.anim.swipe_left
        } else {
            R.anim.swipe_right
        }
        AnimationUtils.loadAnimation(context, attr).apply {
            setAnimationListener(animationListener)
            view.startAnimation(this)
        }
    }

}