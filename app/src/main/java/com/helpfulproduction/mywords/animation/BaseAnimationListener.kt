package com.helpfulproduction.mywords.animation

import android.view.animation.Animation

abstract class BaseAnimationListener: Animation.AnimationListener {
    override fun onAnimationStart(animation: Animation?) {}
    override fun onAnimationEnd(animation: Animation?) {}
    override fun onAnimationRepeat(animation: Animation?) {}
}