package com.helpfulproduction.mywords

import androidx.annotation.StringRes

class SettingsCheckboxItem(
    @StringRes val title: Int,
    @StringRes val subtitleChecked: Int,
    @StringRes val subtitleUnchecked: Int,
    @StringRes val preferenceKey: Int,
    var isChecked: Boolean = false
): SettingsItem() {
    override fun getViewType(): Int = VIEW_TYPE

    companion object {
        const val VIEW_TYPE = R.layout.settings_holder_checkbox
    }
}