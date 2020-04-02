package com.mvwords

import android.os.Bundle
import com.mvwords.mvp.BaseMvpFragment

class CardWordsFragment: BaseMvpFragment<CardWordsContract.Presenter>(), CardWordsContract.View {

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