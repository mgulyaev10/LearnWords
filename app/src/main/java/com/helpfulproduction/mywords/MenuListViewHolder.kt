package com.helpfulproduction.mywords

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MenuListViewHolder(view: View): BaseMenuViewHolder<MenuListItem>(view) {

    private val icon = view.findViewById<ImageView>(R.id.icon)
    private val title = view.findViewById<TextView>(R.id.title)

    override fun bind(menuItem: MenuListItem) {
        icon.apply {
            updateIconTint(menuItem.iconTint)
            setImageResource(menuItem.iconResId)
        }
        title.setText(menuItem.textResId)
        itemView.setOnClickListener {
            menuItem.action.invoke()
        }
    }

    private fun updateIconTint(tint: Int?) {
        if (tint == null) {
            icon.clearColorFilter()
            icon.imageTintList = null
        }
    }
}