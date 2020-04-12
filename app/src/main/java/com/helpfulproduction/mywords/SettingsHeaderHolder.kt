package com.helpfulproduction.mywords

import android.view.View
import android.widget.TextView

class SettingsHeaderHolder(view: View): BaseSettingsHolder<SettingsHeaderItem>(view) {
    override fun bind(item: SettingsHeaderItem) {
        (itemView as? TextView)?.text = itemView.context.getString(item.titleRes)
    }
}