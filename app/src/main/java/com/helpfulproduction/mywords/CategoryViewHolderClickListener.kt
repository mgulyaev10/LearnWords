package com.helpfulproduction.mywords

interface CategoryViewHolderClickListener: ViewHolderClickListener {
    fun onChecked(position: Int, isChecked: Boolean)
}