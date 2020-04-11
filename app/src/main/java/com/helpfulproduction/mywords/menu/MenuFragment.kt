package com.helpfulproduction.mywords.menu

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.MenuItemDecoration
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.ui.PromoFragment
import com.helpfulproduction.mywords.ui.SettingsFragment
import com.helpfulproduction.mywords.utils.Navigator

class MenuFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = MenuAdapter(createMenu())
            addItemDecoration(MenuItemDecoration(view.context, LinearLayoutManager.VERTICAL).apply {
            })
        }
        return view
    }

    private fun createMenu(): List<MenuItem> {
        return listOf(
            MenuListItem(
                R.drawable.ic_diamond_24,
                R.string.buy_pro_version,
                {
                    openPromoFragment()
                },
                iconTint = null,
                lastInBlock = true
            ),
            MenuListItem(
                R.drawable.ic_settings_24,
                R.string.settings,
                {
                    openSettingsFragment()
                },
                lastInBlock = false
            ),
            MenuListItem(
                R.drawable.ic_star_24,
                R.string.rate_app,
                {
                    openRateApp()
                },
                lastInBlock = false
            ),
            MenuListItem (
                R.drawable.ic_feedback_24,
                R.string.feedback,
                {
                    sendFeedbackTg()
                },
                lastInBlock = false
            )
        )
    }

    private fun openSettingsFragment() {
        val fragment = SettingsFragment.Builder()
            .build()
        val title = context?.getString(R.string.settings) ?: "Settings"
        Navigator.go(fragment, actionBarTitle = title)
    }

    private fun openPromoFragment() {
        val fragment = PromoFragment.Builder()
            .build()
        Navigator.openFullScreen(fragment)
    }

    private fun sendFeedbackTg() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/migulyaev")).apply {
            setPackage("org.telegram.messenger")
        }
        trySendFeedback(intent) {
            sendFeedbackWhatsapp()
        }
    }

    private fun sendFeedbackWhatsapp() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/79117857445")).apply {
            setPackage("com.whatsapp")
        }
        trySendFeedback(intent) {
            sendFeedbackVk()
        }
    }

    private fun sendFeedbackVk() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/im?sel=281484485")).apply {
                setPackage("com.vkontakte.android")
            }
        trySendFeedback(intent) {
            Toast.makeText(context, R.string.toast_feedback_error, Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun trySendFeedback(intent: Intent, fallback: () -> Unit) {
        context?.packageManager?.let { packageManager ->
            if (intent.resolveActivity(packageManager) != null) {
                context?.startActivity(intent)
            } else {
                fallback.invoke()
            }
        }
    }

    private fun openRateApp() {
        context?.packageName?.let { packageName ->
            val uri = Uri.parse("market://details?id=$packageName")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }
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