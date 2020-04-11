package com.helpfulproduction.mywords.animation

class CardAnimationListener(
    actionBefore: (() -> Unit)? = null,
    actionAfter: (() -> Unit)? = null,
    val isLeft: Boolean
): AnimationListener(actionBefore, actionAfter)