package com.helpfulproduction.mywords.categories

import com.helpfulproduction.mywords.android.ViewHolderClickListener

interface CategoryViewHolderClickListener: ViewHolderClickListener {
    fun onChecked(position: Int, isChecked: Boolean)
}