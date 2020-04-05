package com.helpfulproduction.mywords.core

class WordFullInfo(categoryId: Int,
                   russian: String,
                   val foreignWord: ForeignWord,
                   status: Int
): Word(categoryId, russian, foreignWord.word, foreignWord.transcription, status)