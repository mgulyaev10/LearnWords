package com.mvwords.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mvwords.R

object Navigator {

    fun go(fragmentManager: FragmentManager?, fragment: Fragment, tag: String, addToBackStack: Boolean = true) {
        if (fragmentManager == null) {
            return
        }

        val transaction = fragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

}