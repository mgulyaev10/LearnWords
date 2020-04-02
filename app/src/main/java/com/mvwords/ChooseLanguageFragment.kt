package com.mvwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseLanguageFragment: BaseMvpFragment<ChooseLanguageContract.Presenter>(), ChooseLanguageContract.View {

    private var title: TextView? = null
    private var recycler: RecyclerView? = null

    private var isNativeLanguage = true

    init {
        presenter = ChooseLanguagePresenter(this)
    }

    override fun isNativeLanguage(): Boolean {
        return isNativeLanguage
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_choose_language, container, false)
        unpackArguments()
        initViews(view)
        return view
    }

    override fun showStudiedLanguage() {
        val fragment = Builder(isNativeLanguage = false)
            .build()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, fragment, TAG_STUDIED_LANGSCREEN)
            ?.addToBackStack(TAG_NATIVE_LANG_SCREEN)
            ?.commit()
    }

    override fun openCardsFragment() {
        val fragment = CardWordsFragment.Builder(isNew = false)
            .build()
        val tag = CardWordsFragment.TAG
        Navigator.goAndClear(activity, fragment, tag)
    }

    private fun unpackArguments() {
        arguments?.let { args ->
            isNativeLanguage = args.getBoolean(ARGS_NATIVE_LANGUAGE, false)
        }
    }

    private fun initViews(view: View) {
        title = view.findViewById<TextView>(R.id.title).apply {
            text = context.getString(getTitleResId())
        }

        recycler = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CountryAdapter(presenter!!)
        }
    }

    @StringRes
    private fun getTitleResId(): Int {
        return if (isNativeLanguage) {
            R.string.title_choose_native_language
        } else {
            R.string.title_choose_target_language
        }
    }

    class Builder(isNativeLanguage: Boolean) {

        private val args = Bundle()

        init {
            args.putBoolean(ARGS_NATIVE_LANGUAGE, isNativeLanguage)
        }

        fun build(): ChooseLanguageFragment {
            return ChooseLanguageFragment().apply {
                arguments = args
            }
        }
    }

    companion object {
        private const val ARGS_NATIVE_LANGUAGE = "is_native_language"
        val TAG_NATIVE_LANG_SCREEN = ChooseLanguageFragment::class.java.simpleName + "_native"
        val TAG_STUDIED_LANGSCREEN = ChooseLanguageFragment::class.java.simpleName + "_studied"
    }

}