package com.helpfulproduction.mywords.core

class WordFullInfo(russian: String,
                   val foreignWord: ForeignWord,
                   status: Int): Word(russian, foreignWord.word, foreignWord.transcription, status)