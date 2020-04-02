package com.mvwords

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object Navigator {

    fun goAndClear(activity: FragmentActivity?, fragment: Fragment, tag: String) {
        go(activity, fragment, tag, clearContainer = true)
    }

    fun go(activity: FragmentActivity?, fragment: Fragment, tag: String) {
        go(activity, fragment, tag, clearContainer = false)
    }

    private fun go(activity: FragmentActivity?, fragment: Fragment, tag: String, clearContainer: Boolean) {
        if (activity == null) {
            return
        }
        val transaction = if (clearContainer) {
            removeAll(activity.supportFragmentManager)
        } else {
            activity.supportFragmentManager.beginTransaction()
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