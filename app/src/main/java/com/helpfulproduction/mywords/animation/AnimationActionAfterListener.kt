package com.helpfulproduction.mywords.animation

import android.view.animation.Animation

open class AnimationActionAfterListener(
    val actionAfter: () -> Unit
): BaseAnimationListener() {

    override fun onAnimationEnd(animation: Animation?) {
        actionAfter.invoke()
    }
}