package com.helpfulproduction.mywords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SettingsAdapter(
    private val items: List<SettingsItem>
): RecyclerView.Adapter<BaseSettingsHolder<SettingsItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSettingsHolder<SettingsItem> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val holder = when (viewType) {
            SettingsHeaderItem.VIEW_TYPE -> SettingsHeaderHolder(view)
            SettingsCheckboxItem.VIEW_TYPE -> SettingsCheckboxHolder(view)
            else -> throw IllegalStateException("Unsupported type")
        }
        return holder as BaseSettingsHolder<SettingsItem>
    }

    override fun onBindViewHolder(holder: BaseSettingsHolder<SettingsItem>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }
}