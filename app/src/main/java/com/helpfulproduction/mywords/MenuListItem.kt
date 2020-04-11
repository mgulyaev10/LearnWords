package com.helpfulproduction.mywords

import androidx.annotation.ColorRes

class MenuListItem(
    val iconResId: Int,
    val textResId: Int,
    action: () -> Unit,
    @ColorRes val iconTint: Int? = R.color.bottom_nav_bar_item_active
): MenuItem(action) {
    override fun getViewType() = R.layout.menu_list_item
}