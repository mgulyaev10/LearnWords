package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat
import com.helpfulproduction.mywords.R

class SettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey)
    }

    class Builder {
        fun build(): SettingsFragment {
            return SettingsFragment()
        }
    }

    companion object {
        val TAG = SettingsFragment::class.java.simpleName
    }

}