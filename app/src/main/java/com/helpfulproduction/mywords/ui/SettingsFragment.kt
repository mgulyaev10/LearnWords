package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R

class SettingsFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
//        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
//            layoutManager = LinearLa
//        }
//        view.findViewById<Button>(R.id.clear_database).apply {
//            setOnClickListener {
//                Words.clear()
//            }
//        }
//
//        view.findViewById<Button>(R.id.clear_prefs).apply {
//            setOnClickListener {
//                Preference.clear()
//            }
//        }
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