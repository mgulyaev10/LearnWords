package com.helpfulproduction.mywords.navigation

interface RootViewPagerListener {
    fun onPageChanged(position: Int, toolbarTitle: String?)
    fun onEmptyStack()
    fun onBackPressed(fragmentsCount: Int)
}