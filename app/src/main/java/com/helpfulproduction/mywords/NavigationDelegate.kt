package com.helpfulproduction.mywords

import androidx.fragment.app.Fragment

interface NavigationDelegate {
    fun onBackPressed()
    fun openCategories()
    fun go(fragment: Fragment)
}