package com.helpfulproduction.mywords

import androidx.annotation.LayoutRes

abstract class MenuItem(
    val action: () -> Unit
) {
    @LayoutRes abstract fun getViewType(): Int
}