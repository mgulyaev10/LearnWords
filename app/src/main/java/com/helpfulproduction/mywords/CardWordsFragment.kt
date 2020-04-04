package com.helpfulproduction.mywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.helpfulproduction.mywords.mvp.BaseMvpFragment

class CardWordsFragment: BaseMvpFragment<CardWordsContract.Presenter>(),
    CardWordsContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_words, container, false)
        return view
    }


    class Builder(isNew: Boolean) {
        private val args = Bundle()

        init {
            args.putBoolean(ARGS_IS_NEW_USER, isNew)
        }

        fun build(): CardWordsFragment {
            return CardWordsFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        private const val ARGS_IS_NEW_USER = "args_is_new"
        val TAG = CardWordsFragment::class.java.simpleName
    }
}