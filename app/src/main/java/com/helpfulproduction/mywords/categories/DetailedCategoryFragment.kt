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

class DetailedCategoryFragment: Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var title: TextView

    private var categoryId = 0
    private var categoryTitle = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        unpackArguments()
        title = view.findViewById<TextView>(R.id.title).apply {
            text = categoryTitle
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
            categoryTitle = args.getString(ARGS_CATEGORY_TITLE, context!!.getString(R.string.category_title_default))
        }
    }

    class Builder(categoryId: Int, categoryTitle: String) {
        private val args = Bundle()
        init {
            args.putInt(ARGS_CATEGORY_ID, categoryId)
            args.putString(ARGS_CATEGORY_TITLE, categoryTitle)
        }

        fun build(): DetailedCategoryFragment {
            return DetailedCategoryFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        private const val ARGS_CATEGORY_TITLE = "args_category_title"
        private const val ARGS_CATEGORY_ID = "args_category_id"
        val TAG = DetailedCategoryFragment::class.java.simpleName
    }
}