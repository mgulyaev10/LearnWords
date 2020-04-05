package com.helpfulproduction.mywords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.helpfulproduction.mywords.utils.Preference
import com.helpfulproduction.mywords.core.Words

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Words.initAsync(this)
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
        val fragment = HomeFragment.Builder(isFirstLaunch).build()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, HomeFragment.TAG)
            .commit()
    }

}
