package com.helpfulproduction.mywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helpfulproduction.mywords.categories.CategoriesFragment
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.utils.Preference
import java.util.*

class HomeFragment: Fragment(), NavigationDelegate {

    private lateinit var rootFragments: List<Stack<Fragment>>
    private lateinit var rootViewPager: RootViewPager
    private lateinit var rootViewPagerAdapter: RootViewPagerAdapter
    private val rootViewPagerListener = object: RootViewPagerListener {
        override fun onPageChanged(position: Int) {
            bottomNavigationView.menu.getItem(position).isChecked = true
        }

        override fun onEmptyStack() {
            ExitManager.tryExit(activity)
        }
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private val menuClickListener = object: BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            onMenuItemSelected(item)
            return true
        }

        override fun onNavigationItemReselected(item: MenuItem) {
            rootViewPagerAdapter.onPageReselected()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val isFirstLaunch = arguments?.getBoolean(ARGS_FIRST_LAUNCH) ?: false
        if (isFirstLaunch) {
            Preference.setFirstLaunch(context)
        }

        Navigator.init(this)

        bottomNavigationView = view.findViewById<BottomNavigationView>(
            R.id.bottom_nav_view
        ).apply {
            setOnNavigationItemSelectedListener(menuClickListener)
            setOnNavigationItemReselectedListener(menuClickListener)
        }

        initRootViewPager(view, isFirstLaunch)

        return view
    }

    override fun onBackPressed() {
        rootViewPagerAdapter.onBackPressed()
        return
    }

    override fun openCategories() {
        onMenuItemSelected(bottomNavigationView.menu.findItem(R.id.menu_categories))
    }

    override fun go(fragment: Fragment) {
        rootViewPagerAdapter.openInCurrentStack(fragment)
    }

    private fun createRootFragments(isFirstLaunch: Boolean): List<Stack<Fragment>> {
        val firstPage = Stack<Fragment>().apply {
            push(createCardWordsFragment())
        }
        val secondPage = Stack<Fragment>().apply {
            push(createCategoriesFragment(isFirstLaunch))
        }
        val thirdPage = Stack<Fragment>().apply {
            push(createSettingsFragment())
        }
        return arrayListOf(
            firstPage,
            secondPage,
            thirdPage
        )
    }

    private fun initRootViewPager(view: View, isFirstLaunch: Boolean) {
        rootFragments = createRootFragments(isFirstLaunch)
        rootViewPagerAdapter = RootViewPagerAdapter(childFragmentManager, rootFragments, rootViewPagerListener)
        rootViewPager = view.findViewById<RootViewPager>(R.id.root_view_pager).apply {
            adapter = rootViewPagerAdapter
            currentItem = if (isFirstLaunch) 1 else 0
            offscreenPageLimit = rootFragments.size
        }
    }

    private fun createCategoriesFragment(isFirstLaunch: Boolean = false): Fragment {
        return CategoriesFragment.Builder(isFirstLaunch = isFirstLaunch)
            .build()
    }

    private fun createCardWordsFragment(): Fragment {
        return CardWordsFragment.Builder(isNew = false)
            .build()
    }

    private fun createSettingsFragment(): Fragment {
        return SettingsFragment.Builder()
            .build()
    }

    private fun onMenuItemSelected(item: MenuItem) {
        val pageId = (bottomNavigationView.menu as MenuBuilder).findItemIndex(item.itemId)
        rootViewPager.setCurrentItem(pageId, false)
    }

    class Builder(isFirstLaunch: Boolean = false) {
        private val args = Bundle()

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
