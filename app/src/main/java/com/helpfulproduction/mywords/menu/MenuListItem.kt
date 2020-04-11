package com.helpfulproduction.mywords.menu

import androidx.annotation.ColorRes
import com.helpfulproduction.mywords.R

class MenuListItem(
    val iconResId: Int,
    val textResId: Int,
    action: () -> Unit,
    lastInBlock: Boolean,
    @ColorRes val iconTint: Int? = R.color.bottom_nav_bar_item_active
): MenuItem(action, lastInBlock) {
    override fun getViewType() =
        R.layout.menu_list_item
}