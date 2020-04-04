package com.helpfulproduction.mywords.core

class CategoryFullInfo(
    @Category.CategoryIds id: Int,
    title: String,
    val words: List<WordFullInfo>
): Category(id,title)