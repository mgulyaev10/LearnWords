package com.helpfulproduction.mywords

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

object AdsManager {
    private val TAG = AdsManager::class.java.simpleName

    private const val ADS_INTERVAL = 4
    private lateinit var interstitialAd: InterstitialAd
    private var wordsCounter = 0
    private val interstitialAdListener = object: AdListener() {
        override fun onAdClosed() {
            interstitialAd.loadAd(AdRequest.Builder().build())
        }
    }

    fun init(context: Context?) {
        interstitialAd = InterstitialAd(context).apply {
            adUnitId = "ca-app-pub-3940256099942544/1033173712" // TODO: Replace id by unique ID
            adListener = interstitialAdListener
        }
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    fun onCardShow() {
        if (wordsCounter >= ADS_INTERVAL) {
            val isShown = showInterstitialAd()
            if (isShown) {
                wordsCounter = 0
                return
            }
        }
        wordsCounter++
    }

    private fun showInterstitialAd(): Boolean {
        val isLoaded = interstitialAd.isLoaded
        if (isLoaded) {
            interstitialAd.show()
        } else {
            Log.e(TAG, "interstitialAd is not loaded!")
        }
        return isLoaded
    }

}