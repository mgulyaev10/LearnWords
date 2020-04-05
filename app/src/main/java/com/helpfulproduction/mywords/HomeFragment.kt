package com.helpfulproduction.mywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helpfulproduction.mywords.categories.CategoriesFragment
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.utils.Preference

class HomeFragment: Fragment(), NavigationDelegate {

    private lateinit var bottomNavigationView: InteractiveBottomNavigationView
    private val menuClickListener = object: BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            onMenuItemSelected(item)
            return true
        }

        override fun onNavigationItemReselected(item: MenuItem) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        bottomNavigationView = view.findViewById<InteractiveBottomNavigationView>(
            R.id.bottom_nav_view
        ).apply {
            setOnNavigationItemSelectedListener(menuClickListener)
            setOnNavigationItemReselectedListener(menuClickListener)
        }

        Navigator.setListener(bottomNavigationView)

        val isFirstLaunch = arguments?.getBoolean(ARGS_FIRST_LAUNCH) ?: false
        if (isFirstLaunch) {
            showCategoriesFragment(isFirstLaunch)
        } else {
            showCardFragment()
        }

        return view
    }

    override fun onDestroyView() {
        Navigator.removeListener()
        super.onDestroyView()
    }

    override fun onBackPressed() {
        if (childFragmentManager.backStackEntryCount >= 1) {
            Navigator.goBack(childFragmentManager)
        } else {
            ExitManager.tryExit(activity)
        }
        return
    }

    private fun showCategoriesFragment(isFirstLaunch: Boolean = false) {
        val fragment = CategoriesFragment.Builder(isFirstLaunch = true)
            .build()
        if (isFirstLaunch) {
            Preference.setFirstLaunch(context)
        }
        Navigator.go(childFragmentManager, fragment, CategoriesFragment.TAG)
    }

    private fun showCardFragment() {
        val fragment = CardWordsFragment.Builder(isNew = false)
            .build()
        val tag = CardWordsFragment.TAG
        Navigator.go(childFragmentManager, fragment, tag)
    }

    private fun showSettingsFragment() {
        val fragment = SettingsFragment.Builder()
            .build()
        val tag = SettingsFragment.TAG
        Navigator.go(childFragmentManager, fragment, tag)
    }

    private fun onMenuItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_learn -> showCardFragment()
            R.id.menu_categories -> showCategoriesFragment()
            R.id.menu_settings -> showSettingsFragment()
        }
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
