package com.helpfulproduction.mywords.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.helpfulproduction.mywords.IntroFragment
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.android.Preference
import com.helpfulproduction.mywords.android.ToastUtils
import com.helpfulproduction.mywords.android.setGone
import com.helpfulproduction.mywords.navigation.ScrolledToTop
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.core.Category
import com.helpfulproduction.mywords.core.Words

class CategoriesFragment: Fragment(), ScrolledToTop {

    private lateinit var recycler: RecyclerView
    private lateinit var title: TextView
    private lateinit var appbar: AppBarLayout

    private var isFirstLaunch = false

    private val categoryClickListener = object : CategoryClickListener {
        override fun onClick(category: Category) {
            if (Preference.isDataUnpacked(context)) {
                openDetailedCategoryFragment(category)
            } else {
                ToastUtils.showLoadingToast(context)
            }
        }

        override fun onChecked(category: Category, isChecked: Boolean) {
            Words.onCategoryCheck(category, isChecked)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        isFirstLaunch = arguments?.getBoolean(ARGS_FIRST_LAUNCH, false) ?: false
        initViews(view)
        return view
    }

    override fun scrollToTop() {
        appbar.setExpanded(true, true)
        recycler.scrollToPosition(0)
    }

    override fun onResume() {
        super.onResume()
        showIntroIfNeed()
    }

    private fun initViews(view: View) {
        view.findViewById<ImageView>(R.id.back).setGone()
        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = CategoriesAdapter(categoryClickListener)
        }
        title = view.findViewById<TextView>(R.id.title).apply {
            text = view.context.getString(R.string.categories)
        }
        appbar = view.findViewById(R.id.appbar)
    }

    private fun openDetailedCategoryFragment(category: Category) {
        val fragment = DetailedCategoryFragment.Builder(
            category.id,
            category.title
        )
            .build()
        Navigator.go(fragment)
    }

    private fun showIntroIfNeed() {
        if (!isFirstLaunch) {
            return
        }
        isFirstLaunch = false
        val introFragment = IntroFragment.Builder()
            .build()
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, introFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    class Builder(isFirstLaunch: Boolean = false) {
        private val args = Bundle()

        init {
            args.putBoolean(ARGS_FIRST_LAUNCH, isFirstLaunch)
        }

        fun build(): CategoriesFragment {
            return CategoriesFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        val TAG = CategoriesFragment::class.java.simpleName
        private const val ARGS_FIRST_LAUNCH = "args_first_launch"
    }
}