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
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.core.Category
import com.helpfulproduction.mywords.core.Words

class CategoriesFragment: Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var title: TextView

    private val categoryClickListener = object : CategoryClickListener {
        override fun onClick(category: Category) {
            openDetailedCategoryFragment(category)
        }

        override fun onChecked(category: Category, isChecked: Boolean) {
            Words.onCategoryCheck(category, isChecked)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter =
                CategoriesAdapter(categoryClickListener)
        }
        title = view.findViewById<TextView>(R.id.title).apply {
            text = view.context.getString(R.string.categories)
        }

    }

    private fun openDetailedCategoryFragment(category: Category) {
        val fragment = DetailedCategoryFragment.Builder(
            category.id,
            category.title
        )
            .build()
        Navigator.go(fragment)
    }

    class Builder(isFirstLaunch: Boolean = false) {
        val args = Bundle()

        init {
            args.putBoolean(TAG, isFirstLaunch)
        }

        fun build(): CategoriesFragment {
            return CategoriesFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        val TAG = CategoriesFragment::class.java.simpleName
    }
}