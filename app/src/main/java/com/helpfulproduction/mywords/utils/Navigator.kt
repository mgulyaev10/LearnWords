package com.helpfulproduction.mywords.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.helpfulproduction.mywords.R

object Navigator {

    private var listener: NavigationListener? = null

    fun go(fragmentManager: FragmentManager?, fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        if (fragmentManager == null) {
            return
        }

        val transaction = fragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        listener?.onFragmentTop(tag)
        transaction.commit()
    }

    fun goBack(fragmentManager: FragmentManager) {
        val tag = fragmentManager.fragments[fragmentManager.fragments.size - 2].tag ?: ""
        fragmentManager.popBackStack()
        listener?.onFragmentTop(tag)
    }

    fun setListener(listener: NavigationListener) {
        Navigator.listener = listener
    }

    fun removeListener() {
        listener = null
    }

    interface NavigationListener {
        fun onFragmentTop(tag: String)
    }

}