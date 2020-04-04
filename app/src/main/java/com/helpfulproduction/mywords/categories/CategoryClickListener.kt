package com.helpfulproduction.mywords.categories

import com.helpfulproduction.mywords.core.Category

interface CategoryClickListener {
    fun onClick(category: Category)
}