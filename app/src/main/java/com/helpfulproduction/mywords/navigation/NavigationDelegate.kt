package com.helpfulproduction.mywords.navigation

import androidx.fragment.app.Fragment

interface NavigationDelegate {
    fun onBackPressed()
    fun openCategories()
    fun go(fragment: Fragment)
}