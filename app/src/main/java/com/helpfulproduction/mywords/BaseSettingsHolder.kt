package com.helpfulproduction.mywords

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSettingsHolder<T: SettingsItem>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}