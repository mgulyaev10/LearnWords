package com.helpfulproduction.mywords.categories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.CategoryViewHolderClickListener
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.core.Category

class CategoryViewHolder(
    view: View,
    clickListener: CategoryViewHolderClickListener
): RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.image)
    private val title: TextView = view.findViewById(R.id.title)
    private val checkbox: AppCompatCheckBox = view.findViewById(R.id.checkbox)

    init {
        view.setOnClickListener {
            clickListener.onClick(adapterPosition)
        }
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!buttonView.isPressed) {
                return@setOnCheckedChangeListener
            }
            clickListener.onChecked(adapterPosition, isChecked)
        }
    }

    fun bind(category: Category) {
        image.setImageDrawable(ContextCompat.getDrawable(itemView.context, category.getIcon()))
        title.text = category.title
        checkbox.isChecked = category.isSelected
    }

}