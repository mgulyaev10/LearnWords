package com.helpfulproduction.mywords.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.ViewHolderClickListener
import com.helpfulproduction.mywords.core.Words

class CategoriesAdapter(
    private val categoryClickListener: CategoryClickListener
): RecyclerView.Adapter<CategoryViewHolder>() {

    private val clickListener = object: ViewHolderClickListener {
        override fun onClick(position: Int) {
            categoryClickListener.onClick(Words.dictionary.categories[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_holder, parent, false)
        return CategoryViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(Words.dictionary.categories[position])
    }

    override fun getItemCount(): Int {
        return Words.dictionary.categories.size
    }
}