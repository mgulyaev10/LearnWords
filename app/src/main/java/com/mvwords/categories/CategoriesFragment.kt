package com.mvwords.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvwords.R

class CategoriesFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        initViews(view)
//        val isFirstLaunch =
        return view
    }

    private fun initViews(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = CategoriesAdapter()
        }
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