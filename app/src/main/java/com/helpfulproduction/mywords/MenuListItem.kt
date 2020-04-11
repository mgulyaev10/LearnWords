package com.helpfulproduction.mywords

class MenuListItem(
    val iconResId: Int,
    val textResId: Int,
    action: () -> Unit
): MenuItem(action) {
    override fun getViewType() = R.layout.menu_list_item
}