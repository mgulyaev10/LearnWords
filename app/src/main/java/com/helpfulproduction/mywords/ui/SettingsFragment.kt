package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.helpfulproduction.mywords.*
import com.helpfulproduction.mywords.android.setGone
import com.helpfulproduction.mywords.android.setVisible
import com.helpfulproduction.mywords.utils.Navigator

class SettingsFragment: Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var title: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        view.findViewById<ImageView>(R.id.back).setGone()
        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = SettingsAdapter(createSettingsItems())
        }
        title = view.findViewById<TextView>(R.id.title).apply {
            text = view.context.getString(R.string.settings)
        }
        view.findViewById<ImageView>(R.id.back).apply {
            setVisible()
            setOnClickListener {
                Navigator.onBackPressed()
            }
        }
        view.findViewById<FrameLayout>(R.id.toolbar).apply {
            (layoutParams as AppBarLayout.LayoutParams).scrollFlags = 0
        }
    }

    private fun createSettingsItems(): List<SettingsItem> {
        val settings = arrayListOf<SettingsItem>()
        settings.addAll(createCommon())
        return settings
    }

    private fun createCommon(): List<SettingsItem> {
        val result = arrayListOf<SettingsItem>()
        result.add(createHeaderItem(R.string.common))
        result.add(SettingsCheckboxItem(R.string.settings_bad_device,
            R.string.settings_bad_device_subtitle_checked,
            R.string.settings_bad_device_subtitle_unchecked,
            R.string.key_bad_device)
        )
        return result
    }

    private fun createHeaderItem(@StringRes titleRes: Int): SettingsHeaderItem = SettingsHeaderItem(titleRes)

    class Builder {
        fun build(): SettingsFragment {
            return SettingsFragment()
        }
    }

    companion object {
        val TAG = SettingsFragment::class.java.simpleName
    }

}