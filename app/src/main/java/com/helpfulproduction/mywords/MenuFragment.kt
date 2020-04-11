package com.helpfulproduction.mywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.ui.SettingsFragment
import com.helpfulproduction.mywords.utils.Navigator

class MenuFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = MenuAdapter(createMenu())
        }
        return view
    }

    private fun createMenu(): List<MenuItem> {
        return listOf(
            MenuListItem(R.drawable.ic_diamond_24, R.string.buy_pro_version, {
                openPromoFragment()
            }, iconTint = null),
            MenuListItem(R.drawable.ic_settings_24, R.string.settings, {
                openSettingsFragment()
            })
        )
    }

    private fun openSettingsFragment() {
        val fragment = SettingsFragment.Builder()
            .build()
        Navigator.go(fragment)
    }

    private fun openPromoFragment() {
        val fragment = PromoFragment.Builder()
            .build()
        Navigator.openFullScreen(fragment)
    }

    class Builder {
        private val args = Bundle()

        fun build(): MenuFragment {
            return MenuFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        val TAG = MenuFragment::class.java.simpleName
    }
}