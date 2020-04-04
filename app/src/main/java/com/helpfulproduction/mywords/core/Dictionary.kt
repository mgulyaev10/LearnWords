package com.helpfulproduction.mywords.core

data class Dictionary<T: Category>(
    val categories: List<T>
)