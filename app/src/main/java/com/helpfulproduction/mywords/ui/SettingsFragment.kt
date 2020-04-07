package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.core.Words
import com.helpfulproduction.mywords.android.Preference

class SettingsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view.findViewById<Button>(R.id.clear_database).apply {
            setOnClickListener {
                Words.clear()
            }
        }

        view.findViewById<Button>(R.id.clear_prefs).apply {
            setOnClickListener {
                Preference.clear()
            }
        }
        return view
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