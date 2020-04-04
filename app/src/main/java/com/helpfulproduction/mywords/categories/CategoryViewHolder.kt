package com.helpfulproduction.mywords.categories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.ViewHolderClickListener
import com.helpfulproduction.mywords.core.Category

class CategoryViewHolder(
    view: View,
    clickListener: ViewHolderClickListener
): RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.image)
    private val title: TextView = view.findViewById(R.id.title)
    private val checkbox: AppCompatCheckBox = view.findViewById(R.id.checkbox)

    init {
        view.setOnClickListener {
            clickListener.onClick(adapterPosition)
        }
    }

    fun bind(category: Category) {
        image.setImageDrawable(ContextCompat.getDrawable(itemView.context, category.getIcon()))
        title.text = category.title
        checkbox.isChecked = true
    }

}