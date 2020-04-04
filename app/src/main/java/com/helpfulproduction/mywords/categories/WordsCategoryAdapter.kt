package com.helpfulproduction.mywords.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.core.Word
import com.helpfulproduction.mywords.core.Words
import io.reactivex.rxjava3.schedulers.Schedulers

class WordsCategoryAdapter(
    categoryId: Int
): RecyclerView.Adapter<WordCategoryHolder>() {

    private var words = emptyList<Word>()

    init {
        Words.getWordsByCategoryId(categoryId)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                words = it
                notifyDataSetChanged()
            }, {
                it.printStackTrace()
            })
    }

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

    private companion object {
        val TAG = WordsCategoryAdapter::class.java.simpleName
    }
}