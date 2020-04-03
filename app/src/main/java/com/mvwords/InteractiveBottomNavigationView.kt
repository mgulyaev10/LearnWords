package com.mvwords

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mvwords.categories.CategoriesFragment
import com.mvwords.utils.Navigator

class InteractiveBottomNavigationView: BottomNavigationView, Navigator.NavigationListener {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFragmentTop(tag: String) {
        if (!fragments.containsKey(tag)) {
            return
        }

        menu.getItem(fragments[tag] ?: error("Not such fragment $tag in menu")).isChecked = true
    }

    private companion object {
        val fragments = mapOf(
            CardWordsFragment.TAG to 0,
            CategoriesFragment.TAG to 1,
            SettingsFragment.TAG to 2
        )
    }

}