package com.mvwords

import androidx.fragment.app.Fragment

class SettingsFragment: Fragment() {

    class Builder {
        fun build(): SettingsFragment {
            return SettingsFragment()
        }
    }
    companion object {
        val TAG = SettingsFragment::class.java.simpleName
    }
}