package com.helpfulproduction.mywords

import androidx.fragment.app.Fragment

data class FragmentWrapper(
    val fragment: Fragment,
    val toolbarTitle: String? = null
)