package com.helpfulproduction.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.helpfulproduction.mywords.AdsManager
import com.helpfulproduction.mywords.R
import com.helpfulproduction.mywords.android.ThreadUtils
import com.helpfulproduction.mywords.core.Word
import com.helpfulproduction.mywords.core.Words
import com.helpfulproduction.mywords.mvp.BaseMvpFragment
import com.helpfulproduction.mywords.android.setGone
import com.helpfulproduction.mywords.android.setVisible
import com.helpfulproduction.mywords.cards.CardWordsContract
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.utils.SpeechHelper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class CardWordsFragment: BaseMvpFragment<CardWordsContract.Presenter>(),
    CardWordsContract.View {

    private lateinit var category: TextView
    private lateinit var russian: TextView
    private lateinit var english: TextView
    private lateinit var transcription: TextView
    private lateinit var speechButton: ImageView
    private lateinit var content: View
    private lateinit var stub: View
    private lateinit var loading: ProgressBar
    private lateinit var chooseCategories: Button
    private lateinit var knownWord: TextView
    private lateinit var unknownWord: TextView

    private var words: List<Word>? = null
    private var currentWord: Word? = null
    private var index = -1

    private var wordsUpdateListener = object: Words.WordsUpdateListener {
        override fun onWordsUpdated(words: List<Word>) {
            this@CardWordsFragment.words = words
            val needUpdateUi = !words.contains(currentWord)
            index = -1
            if (needUpdateUi) {
                ThreadUtils.postOnMainThread {
                    updateUi()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_words, container, false)
        initViews(view)

        Words.registerWordsUpdateListener(wordsUpdateListener)
        words = Words.getWords()
        updateUi()

        return view
    }

    override fun onDestroyView() {
        Words.unregisterWordsUpdateListener()
        super.onDestroyView()
    }

    private fun updateUi() {
        ThreadUtils.assertMainThread()
        val currentWords = words
        when {
            currentWords?.isEmpty() == true -> {
                showStub()
            }
            currentWords != null -> {
                updateCard(currentWords)
            }
            else -> {
                showLoading()
                Words.reload()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        words = it
                        if (it == null) {
                            showStub()
                        } else {
                            updateCard(it)
                        }
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }

    private fun showLoading() {
        loading.setVisible()
        content.setGone()
        stub.setGone()
    }

    private fun showStub() {
        stub.setVisible()
        loading.setGone()
        content.setGone()
    }

    private fun showCard() {
        content.setVisible()
        stub.setGone()
        loading.setGone()
    }

    private fun updateCard(currentWords: List<Word>) {
        if (index < currentWords.size - 1) {
            index++
        } else {
            index = 0
        }
        AdsManager.onCardShow()
        val currentWord = currentWords[index]
        this.currentWord = currentWord
        category.text = Words.categoryFromId(currentWord.categoryId)
        russian.text = currentWord.russian
        english.text = currentWord.english
        transcription.text = currentWord.transcription
        showCard()
    }

    private fun initViews(view: View) {
        category = view.findViewById(R.id.category)
        russian = view.findViewById(R.id.russian)
        english = view.findViewById(R.id.english)
        transcription = view.findViewById(R.id.transcription)
        speechButton = view.findViewById<ImageView>(R.id.voice).apply {
            setOnClickListener {
                words?.get(index)?.let { word ->
                    SpeechHelper.speak(context, word.english)
                }
            }
        }
        content = view.findViewById(R.id.content)
        stub = view.findViewById(R.id.stub)
        loading = view.findViewById(R.id.loading)
        chooseCategories = view.findViewById<Button>(R.id.choose_categories).apply {
            setOnClickListener {
                Navigator.openCategoriesFragment()
            }
        }
        knownWord = view.findViewById<TextView>(R.id.known_word).apply {
            setOnClickListener {
                words?.let {
                    updateCard(it)
                }
            }
        }
        unknownWord = view.findViewById<TextView>(R.id.unknown_word).apply {
            setOnClickListener {
                words?.let {
                    updateCard(it)
                }
            }
        }
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