package com.helpfulproduction.mywords

import androidx.annotation.StringRes

class SettingsHeaderItem(
    @StringRes val titleRes: Int
): SettingsItem() {
    override fun getViewType(): Int {
        return VIEW_TYPE
    }

    companion object {
        const val VIEW_TYPE = R.layout.settings_header_item
    }
}