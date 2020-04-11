package com.helpfulproduction.mywords.navigation

import androidx.fragment.app.Fragment

data class FragmentWrapper(
    val fragment: Fragment,
    val toolbarTitle: String? = null
)