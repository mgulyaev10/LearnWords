package com.helpfulproduction.mywords.menu

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.helpfulproduction.mywords.R

class MenuListViewHolder(view: View): BaseMenuViewHolder<MenuListItem>(view) {

    private val icon = view.findViewById<ImageView>(R.id.icon)
    private val title = view.findViewById<TextView>(R.id.title)

    override fun bind(menuItem: MenuListItem) {
        icon.apply {
            updateIconTint(menuItem.iconTint)
            setImageResource(menuItem.iconResId)
        }
        title.apply {
            setText(menuItem.textResId)
            updateTextColor(menuItem.textColor)
        }
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

    private fun updateTextColor(color: Int?) {
        if (color != null) {
            title.setTextColor(ContextCompat.getColor(itemView.context, color))
        }
    }
}