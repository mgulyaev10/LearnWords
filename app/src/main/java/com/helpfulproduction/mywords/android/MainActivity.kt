package com.helpfulproduction.mywords.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.MobileAds
import com.helpfulproduction.mywords.ui.HomeFragment
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.navigation.NavigationDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMobileAds()
        openHomeFragment()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is NavigationDelegate) {
                it.onBackPressed()
                return
            }
        }
        super.onBackPressed()
    }

    private fun openHomeFragment() {
        if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) != null) {
            return
        }
        val isFirstLaunch = Preference.isFirstLaunch(this)
        val fragment = HomeFragment.Builder(
            isFirstLaunch
        ).build()

        supportFragmentManager.beginTransaction()
            .add(
                R.id.container, fragment,
                HomeFragment.TAG
            )
            .commit()
    }

    private fun initMobileAds() {
        MobileAds.initialize(this)
    }

}
