package com.helpfulproduction.mywords.categories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.utils.SpeechHelper
import com.helpfulproduction.mywords.core.Word

class WordCategoryHolder(view: View): RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.title)
    private val subtitle: TextView = view.findViewById(R.id.subtitle)

    init {
        view.findViewById<ImageView>(R.id.voice).apply {
            setOnClickListener {
                SpeechHelper.speak(view.context, title.text)
            }
        }
    }

    fun bind(word: Word) {
        title.text = word.english
        subtitle.text = word.russian
    }

}