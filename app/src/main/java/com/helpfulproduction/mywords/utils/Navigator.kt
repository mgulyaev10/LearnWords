package com.helpfulproduction.mywords.utils

import androidx.fragment.app.Fragment
import com.helpfulproduction.mywords.NavigationDelegate

object Navigator {

    private lateinit var navigationDelegate: NavigationDelegate

    fun init(navigationDelegate: NavigationDelegate) {
        this.navigationDelegate = navigationDelegate
    }

    fun openCategoriesFragment() {
        navigationDelegate.openCategories()
    }

    fun go(fragment: Fragment) {
        navigationDelegate.go(fragment)
    }

}