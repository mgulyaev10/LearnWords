package com.mvwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvwords.utils.Navigator
import com.mvwords.utils.Preference
import core.Words

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Words.init(this)
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
        val fragment = HomeFragment.Builder(Preference.isFirstLaunch(this))
            .build()
        Navigator.go(supportFragmentManager, fragment, HomeFragment.TAG, addToBackStack = false)
    }

}
