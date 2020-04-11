package com.helpfulproduction.mywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.helpfulproduction.mywords.utils.Navigator

class PromoFragment: Fragment() {

    private var flags: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_promo, container, false)
        view.findViewById<ImageView>(R.id.close).apply {
            setOnClickListener {
                Navigator.onBackPressed()
            }
        }

        activity?.window?.decorView?.systemUiVisibility?.let {
            makeFullScreen(it)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resetFlags()
    }

    private fun makeFullScreen(currentFlags: Int) {
        activity?.window?.decorView?.systemUiVisibility = currentFlags or fullScreenFlags
    }

    private fun resetFlags() {
        activity?.window?.decorView?.systemUiVisibility?.let { flags ->
            activity?.window?.decorView?.systemUiVisibility = flags xor fullScreenFlags
        }
    }

    class Builder {
        fun build(): PromoFragment {
            return PromoFragment()
        }
    }

    companion object {
        val TAG = PromoFragment::class.java.simpleName
        private const val fullScreenFlags = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

}