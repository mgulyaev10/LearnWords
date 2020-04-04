package com.helpfulproduction.mywords.core

import androidx.annotation.IntDef

open class Word(
    val russian: String,
    val english: String,
    val transcription: String,
    @WordStatus var status: Int
) {

    @IntDef(STATUS_NEW)
    annotation class WordStatus

    companion object {
        const val STATUS_NEW = 0
    }
}