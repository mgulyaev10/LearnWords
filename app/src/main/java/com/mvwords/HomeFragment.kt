package com.mvwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mvwords.categories.CategoriesFragment
import com.mvwords.utils.Navigator

class HomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val isFirstLaunch = arguments?.getBoolean(ARGS_FIRST_LAUNCH) ?: false

        showCategoriesFragment(true)
//        if (isFirstLaunch) {

//        } else {
//            showCardFragment()
//        }
        return view
    }

    private fun showCategoriesFragment(isFirstLaunch: Boolean) {
        val fragment = CategoriesFragment.Builder(isFirstLaunch)
            .build()
        Navigator.go(childFragmentManager, fragment, CategoriesFragment.TAG)
    }

    private fun showCardFragment() {
        val fragment = CardWordsFragment.Builder(isNew = false)
            .build()
        val tag = CardWordsFragment.TAG
        Navigator.go(childFragmentManager, fragment, tag)
    }

    class Builder(isFirstLaunch: Boolean = false) {
        val args = Bundle()

        init {
            args.putBoolean(ARGS_FIRST_LAUNCH, isFirstLaunch)
        }

        fun build(): HomeFragment {
            return HomeFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        private const val ARGS_FIRST_LAUNCH = "args_is_first_launch"
        val TAG = HomeFragment::class.java.simpleName
    }
}
