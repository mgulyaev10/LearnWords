package com.helpfulproduction.mywords.animation

import android.view.animation.Animation

open class AnimationListener(
    val actionBefore: (() -> Unit)? = null,
    val actionAfter: (() -> Unit)? = null
): BaseAnimationListener() {

    override fun onAnimationStart(animation: Animation?) {
        actionBefore?.invoke()
    }

    override fun onAnimationEnd(animation: Animation?) {
        actionAfter?.invoke()
    }

}