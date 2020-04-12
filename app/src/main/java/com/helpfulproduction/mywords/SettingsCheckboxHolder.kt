package com.helpfulproduction.mywords

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.StringRes
import com.helpfulproduction.mywords.android.Preference

class SettingsCheckboxHolder(view: View): BaseSettingsHolder<SettingsCheckboxItem>(view) {

    private val title = view.findViewById<TextView>(R.id.title)
    private val subtitle = view.findViewById<TextView>(R.id.subtitle)
    private val checkbox = view.findViewById<CheckBox>(R.id.checkbox)

    override fun bind(item: SettingsCheckboxItem) {
        item.isChecked = Preference.getSettings(itemView.context, item.preferenceKey)
        title.text = itemView.context.getString(item.title)
        updateDynamicUi(item)
        itemView.setOnClickListener {
            togglePreference(item)
        }
        checkbox.setOnCheckedChangeListener { view, _ ->
            if (!view.isPressed) {
                return@setOnCheckedChangeListener
            }
            togglePreference(item)
        }
    }

    private fun togglePreference(item: SettingsCheckboxItem) {
        item.isChecked = !item.isChecked
        Preference.setSettings(itemView.context, item.preferenceKey, item.isChecked)
        updateDynamicUi(item)
    }

    private fun updateDynamicUi(item: SettingsCheckboxItem) {
        checkbox.isChecked = item.isChecked
        subtitle.text = itemView.context.getString(resolveSubtitleRes(item.isChecked))
    }

    @StringRes
    private fun resolveSubtitleRes(isChecked: Boolean): Int {
        return if (isChecked) {
            R.string.settings_bad_device_subtitle_checked
        } else {
            R.string.settings_bad_device_subtitle_unchecked
        }
    }

}