package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.utils.Navigator

class PromoFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_promo, container, false)
        view.findViewById<ImageView>(R.id.close).apply {
            setOnClickListener {
                Navigator.onBackPressed()
            }
        }

        return view
    }

    class Builder {
        fun build(): PromoFragment {
            return PromoFragment()
        }
    }

    companion object {
        val TAG = PromoFragment::class.java.simpleName
    }

}