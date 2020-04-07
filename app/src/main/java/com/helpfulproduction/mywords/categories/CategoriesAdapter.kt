package com.helpfulproduction.mywords.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.core.Category
import com.helpfulproduction.mywords.core.Words
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class CategoriesAdapter(
    private val categoryClickListener: CategoryClickListener
): RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories = emptyList<Category>()

    init {
        Words.getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                categories = it
                notifyDataSetChanged()
            }, {
                it.printStackTrace()
            })
    }

    private val clickListener = object: CategoryViewHolderClickListener {
        override fun onClick(position: Int) {
            categoryClickListener.onClick(categories[position])
        }

        override fun onChecked(position: Int, isChecked: Boolean) {
            categoryClickListener.onChecked(categories[position], isChecked)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_holder, parent, false)
        return CategoryViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}