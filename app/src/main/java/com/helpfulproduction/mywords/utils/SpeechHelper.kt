package com.helpfulproduction.mywords.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

object SpeechHelper {

    private var textToSpeech: TextToSpeech? = null
    private val words = PriorityQueue<String>()
    private val listener = object: TextToSpeech.OnInitListener {
        override fun onInit(status: Int) {
            val word = words.poll() ?: return
            textToSpeech?.language = Locale.ENGLISH
            textToSpeech?.speak(word, TextToSpeech.QUEUE_ADD, null, null)
            textToSpeech = null
        }
    }

    fun speak(context: Context?, word: CharSequence) {
        textToSpeech = TextToSpeech(context,
            listener
        )
        words.add(word.toString())
    }

}