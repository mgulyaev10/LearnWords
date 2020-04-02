package com.mvwords

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CountryHolder(
    view: View,
    clickListener: CountryClickListener
): RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.image)
    private val title: TextView = view.findViewById(R.id.title)

    init {
        view.setOnClickListener {
            clickListener.onClick(adapterPosition)
        }
    }

    fun bind(language: Language) {
        image.setImageDrawable(ContextCompat.getDrawable(itemView.context, language.flagRes))
        title.text = itemView.context.getString(language.titleRes)
    }

    interface CountryClickListener {
        fun onClick(position: Int)
    }

}