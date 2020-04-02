package com.mvwords.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvwords.R
import core.Words

class WordsCategoryAdapter(
    categoryId: Int
): RecyclerView.Adapter<WordCategoryHolder>() {

    private val words = Words.dictionary.categories[categoryId].words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_category_holder, parent, false)
        return WordCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: WordCategoryHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }
}