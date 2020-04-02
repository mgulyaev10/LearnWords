package com.mvwords.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mvwords.R

object Navigator {

    fun goAndClear(fragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        go(fragmentManager, fragment, tag, clearContainer = true)
    }

    fun go(fragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        go(fragmentManager, fragment, tag, clearContainer = false)
    }

    private fun go(fragmentManager: FragmentManager?, fragment: Fragment, tag: String, clearContainer: Boolean) {
        if (fragmentManager == null) {
            return
        }
        val transaction = if (clearContainer) {
            removeAll(fragmentManager)
        } else {
            fragmentManager.beginTransaction()
        }
        transaction.add(R.id.container, fragment, tag).commit()
    }

    private fun removeAll(fragmentManager: FragmentManager): FragmentTransaction {
        val transaction = fragmentManager.beginTransaction()
        fragmentManager.fragments.forEach {
            transaction.remove(it)
        }
        return transaction
    }

}