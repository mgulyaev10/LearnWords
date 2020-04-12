package com.helpfulproduction.mywords.menu

import androidx.annotation.ColorRes
import com.helpfulproduction.mywords.R

open class MenuListItem(
    val iconResId: Int,
    val textResId: Int,
    action: () -> Unit,
    lastInBlock: Boolean = false,
    @ColorRes val iconTint: Int? = R.color.bottom_nav_bar_item_active,
    @ColorRes val textColor: Int? = null
): MenuItem(action, lastInBlock) {
    override fun getViewType() =
        R.layout.menu_list_item
}