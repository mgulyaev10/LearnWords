package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helpfulproduction.mywords.AdsManager
import com.helpfulproduction.mywords.navigation.FragmentWrapper
import com.helpfulproduction.mywords.menu.MenuFragment
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.categories.CategoriesFragment
import com.helpfulproduction.mywords.utils.ExitManager
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.android.Preference
import com.helpfulproduction.mywords.android.setGone
import com.helpfulproduction.mywords.android.setVisible
import com.helpfulproduction.mywords.navigation.NavigationDelegate
import com.helpfulproduction.mywords.navigation.RootViewPager
import com.helpfulproduction.mywords.navigation.RootViewPagerAdapter
import com.helpfulproduction.mywords.navigation.RootViewPagerListener
import java.util.*

class HomeFragment: Fragment(), NavigationDelegate {

    private lateinit var toolbar: AppBarLayout
    private lateinit var toolbarTitle: TextView
    private lateinit var rootFragments: List<Stack<FragmentWrapper>>
    private lateinit var rootViewPager: RootViewPager
    private lateinit var rootViewPagerAdapter: RootViewPagerAdapter
    private val rootViewPagerListener = object: RootViewPagerListener {
        override fun onPageChanged(position: Int, toolbarTitle: String?) {
            bottomNavigationView.menu.getItem(position).isChecked = true
            if (toolbarTitle != null) {
                showToolbar(toolbarTitle)
            } else {
                hideToolbar()
            }
        }

        override fun onEmptyStack() {
            ExitManager.tryExit(activity)
        }

        override fun onBackPressed(fragmentsCount: Int) {
            if (fragmentsCount == 1) {
                hideToolbar()
            }
        }
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private val menuClickListener = object: BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            onMenuItemSelected(item)
            hideToolbar()
            return true
        }

        override fun onNavigationItemReselected(item: MenuItem) {
            rootViewPagerAdapter.onPageReselected()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initAndHideToolbar(view)

        val isFirstLaunch = arguments?.getBoolean(ARGS_FIRST_LAUNCH) ?: false
        if (isFirstLaunch) {
            Preference.setFirstLaunch(context)
        }

        Navigator.init(this)
        AdsManager.init(context)

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
        activity?.supportFragmentManager?.backStackEntryCount?.let { count ->
            if (count > 0) {
                activity?.supportFragmentManager?.popBackStack()
                return
            }
        }
        rootViewPagerAdapter.onBackPressed()
    }

    override fun openCategories() {
        onMenuItemSelected(bottomNavigationView.menu.findItem(R.id.menu_categories))
    }

    override fun go(fragment: Fragment, actionBarTitle: String?) {
        rootViewPagerAdapter.openInCurrentStack(fragment, actionBarTitle)
    }

    override fun openFullScreen(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun createRootFragments(isFirstLaunch: Boolean): List<Stack<FragmentWrapper>> {
        val firstPage = Stack<FragmentWrapper>().apply {
            push(createCardWordsFragment())
        }
        val secondPage = Stack<FragmentWrapper>().apply {
            push(createCategoriesFragment(isFirstLaunch))
        }
        val thirdPage = Stack<FragmentWrapper>().apply {
            push(createMenuFragment())
        }
        return arrayListOf(
            firstPage,
            secondPage,
            thirdPage
        )
    }

    private fun initRootViewPager(view: View, isFirstLaunch: Boolean) {
        rootFragments = createRootFragments(isFirstLaunch)
        rootViewPagerAdapter = RootViewPagerAdapter(
                childFragmentManager,
                rootFragments,
                rootViewPagerListener
        )
        rootViewPager = view.findViewById<RootViewPager>(R.id.root_view_pager).apply {
            adapter = rootViewPagerAdapter
            currentItem = if (isFirstLaunch) 1 else 0
            offscreenPageLimit = rootFragments.size
        }
    }

    private fun createCategoriesFragment(isFirstLaunch: Boolean = false): FragmentWrapper {
        return FragmentWrapper(
            CategoriesFragment.Builder(isFirstLaunch = isFirstLaunch)
                .build()
        )
    }

    private fun createCardWordsFragment(): FragmentWrapper {
        return FragmentWrapper(
            CardWordsFragment.Builder(isNew = false)
                .build()
        )
    }

    private fun createMenuFragment(): FragmentWrapper {
        return FragmentWrapper(
            MenuFragment.Builder()
                .build()
        )
    }

    private fun onMenuItemSelected(item: MenuItem) {
        val pageId = (bottomNavigationView.menu as MenuBuilder).findItemIndex(item.itemId)
        rootViewPager.setCurrentItem(pageId, false)
    }

    private fun initAndHideToolbar(view: View) {
        toolbar = view.findViewById<AppBarLayout>(R.id.toolbar).apply {
            findViewById<ImageView>(R.id.back).apply {
                setOnClickListener {
                    Navigator.onBackPressed()
                }
            }
            toolbarTitle = findViewById(R.id.title)
        }
        hideToolbar()
    }

    private fun hideToolbar() {
        toolbar.setGone()
    }

    private fun showToolbar(title: String) {
        toolbar.setVisible()
        toolbarTitle.text = title
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
