package com.helpfulproduction.mywords.menu

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseMenuViewHolder<T: MenuItem>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(menuItem: T)
}