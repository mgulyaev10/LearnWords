package com.helpfulproduction.mywords

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.helpfulproduction.mywords.utils.Navigator

class IntroFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)
        val rect = arguments?.getParcelable(ARGS_RECT) as? Rect
        view.setOnClickListener {
            Navigator.onBackPressed()
        }

        return view
    }

    class Builder {
        private val args = Bundle()

        fun build(): IntroFragment {
            return IntroFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        private const val ARGS_RECT = "args_rect"
    }
}