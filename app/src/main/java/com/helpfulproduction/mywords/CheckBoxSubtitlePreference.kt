package com.helpfulproduction.mywords

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.CompoundButton
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder

class CheckBoxSubtitlePreference: CheckBoxPreference {
    private val listener = Listener()

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.itemView?.findViewById<TextView>(R.id.title)?.text = title
        holder?.itemView?.findViewById<TextView>(R.id.subtitle)?.setText(resolveSubtitleRes())
        holder?.itemView?.findViewById<CheckBox>(R.id.checkbox)?.let {
            syncCheckBoxView(it)
        }
    }

    private fun syncCheckBoxView(view: View) {
        if (view is CompoundButton) {
            view.setOnCheckedChangeListener(null)
        }
        if (view is Checkable) {
            view.isChecked = mChecked
        }
        if (view is CompoundButton) {
            view.setOnCheckedChangeListener(listener)
        }
    }

    @StringRes
    private fun resolveSubtitleRes(): Int {
        return if (isChecked) {
            R.string.settings_bad_device_subtitle_checked
        } else {
            R.string.settings_bad_device_subtitle_unchecked
        }
    }

    private inner class Listener internal constructor() : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(
            buttonView: CompoundButton,
            isChecked: Boolean
        ) {
            if (!callChangeListener(isChecked)) {
                buttonView.isChecked = !isChecked
                return
            }
            setChecked(isChecked)
        }
    }

}