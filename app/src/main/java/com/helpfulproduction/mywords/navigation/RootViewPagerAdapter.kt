package com.helpfulproduction.mywords.navigation

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import java.util.*

class RootViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: List<Stack<Fragment>>,
    private val listener: RootViewPagerListener
): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var currentPosition = 0
    private var changedFragment: Fragment? = null

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        currentPosition = position
        super.setPrimaryItem(container, position, `object`)
        listener.onPageChanged(position)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position].peek()
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItemPosition(`object`: Any): Int {
        if (changedFragment == `object`) {
            changedFragment = null
            return PagerAdapter.POSITION_NONE
        }
        return PagerAdapter.POSITION_UNCHANGED
    }

    fun onBackPressed() {
        if (fragments[currentPosition].size == 1) {
            listener.onEmptyStack()
        } else {
            changedFragment = fragments[currentPosition].pop()
            listener.onBackPressed(fragments[currentPosition].size)
            notifyDataSetChanged()
        }
    }

    fun openInCurrentStack(fragment: Fragment) {
        changedFragment = fragments[currentPosition].peek()
        fragments[currentPosition].push(fragment)
        notifyDataSetChanged()
    }

    fun onPageReselected() {
        val currentStack = fragments[currentPosition]
        val currentFragment = currentStack.peek()
        if (currentStack.size == 1) {
            (currentFragment as? ScrolledToTop)?.scrollToTop()
            return
        }
        changedFragment = currentFragment
        while (currentStack.size > 1) {
           currentStack.pop()
        }
        notifyDataSetChanged()
    }

}