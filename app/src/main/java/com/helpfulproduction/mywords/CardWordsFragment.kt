package com.helpfulproduction.mywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.helpfulproduction.mywords.categories.CategoriesFragment
import com.helpfulproduction.mywords.core.Word
import com.helpfulproduction.mywords.core.Words
import com.helpfulproduction.mywords.mvp.BaseMvpFragment
import com.helpfulproduction.mywords.utils.Navigator
import com.helpfulproduction.mywords.utils.SpeechHelper
import kotlinx.android.synthetic.main.activity_main.view.*

class CardWordsFragment: BaseMvpFragment<CardWordsContract.Presenter>(),
    CardWordsContract.View {

    private lateinit var category: TextView
    private lateinit var russian: TextView
    private lateinit var english: TextView
    private lateinit var transcription: TextView
    private lateinit var speechButton: ImageView
    private lateinit var content: View
    private lateinit var stub: View
    private lateinit var chooseCategories: Button

    private var words: List<Word> = Words.getWords().shuffled()
    private var index = -1

    private var wordsUpdateListener = object: Words.WordsUpdateListener {
        override fun onWordsUpdated(words: List<Word>) {
            this@CardWordsFragment.words = words
            index = -1
            updateUi()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_words, container, false)
        initViews(view)

        Words.registerWordsUpdateListener(wordsUpdateListener)

        return view
    }

    override fun onDestroyView() {
        Words.unregisterWordsUpdateListener()
        super.onDestroyView()
    }

    private fun updateUi() {
        if (words.isEmpty()) {
            showStub()
        } else {
            updateCard()
        }
    }

    private fun showStub() {
        content.setGone()
        stub.setVisible()
    }

    private fun updateCard() {
        if (index != words.size - 1) {
            index++
        } else {
            // TODO: words end
        }
        val currentWord = words[index]
        category.text = Words.categoryFromId(currentWord.categoryId)
        russian.text = currentWord.russian
        english.text = currentWord.english
        transcription.text = currentWord.transcription
        content.setVisible()
        stub.setGone()
    }

    private fun initViews(view: View) {
        category = view.findViewById<TextView>(R.id.category).apply {

        }
        russian = view.findViewById<TextView>(R.id.russian).apply {

        }
        english = view.findViewById<TextView>(R.id.english).apply {

        }
        transcription = view.findViewById<TextView>(R.id.transcription)
        speechButton = view.findViewById<ImageView>(R.id.voice).apply {
            setOnClickListener {
                SpeechHelper.speak(context, words[index].english)
            }
        }
        content = view.findViewById(R.id.content)
        stub = view.findViewById(R.id.stub)
        chooseCategories = view.findViewById<Button>(R.id.choose_categories).apply {
            setOnClickListener {
                Navigator.openCategoriesFragment()
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