package com.helpfulproduction.mywords.menu

import androidx.annotation.LayoutRes

abstract class MenuItem(
    val action: () -> Unit,
    val lastInBlock: Boolean
) {
    @LayoutRes abstract fun getViewType(): Int
}