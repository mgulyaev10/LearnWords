package com.helpfulproduction.mywords

import androidx.annotation.LayoutRes

abstract class SettingsItem(
    val action: (() -> Unit)? = null
) {
    @LayoutRes abstract fun getViewType(): Int
}