package com.helpfulproduction.mywords

class CardAnimationListener(
    actionAfter: () -> Unit,
    val isLeft: Boolean
): AnimationActionAfterListener(actionAfter)