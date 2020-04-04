package com.helpfulproduction.mywords.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R

import com.helpfulproduction.mywords.core.Words

class DetailedCategoryFragment: Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var title: TextView

    private var categoryId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        unpackArguments()
        title = view.findViewById<TextView>(R.id.title).apply {
            text = Words.dictionary.categories[categoryId].title
        }
        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = WordsCategoryAdapter(categoryId)
        }
        return view
    }

    private fun unpackArguments() {
        arguments?.let { args ->
            categoryId = args.getInt(ARGS_CATEGORY_ID, 0)
        }
    }

    class Builder(categoryId: Int) {
        private val args = Bundle()
        init {
            args.putInt(ARGS_CATEGORY_ID, categoryId)
        }

        fun build(): DetailedCategoryFragment {
            return DetailedCategoryFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        private const val ARGS_CATEGORY_ID = "args_category_id"
        val TAG = DetailedCategoryFragment::class.java.simpleName
    }
}