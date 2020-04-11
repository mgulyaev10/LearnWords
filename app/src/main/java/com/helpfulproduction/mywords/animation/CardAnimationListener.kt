package com.helpfulproduction.mywords.animation

import com.helpfulproduction.mywords.animation.AnimationActionAfterListener

class CardAnimationListener(
    actionAfter: () -> Unit,
    val isLeft: Boolean
): AnimationActionAfterListener(actionAfter)