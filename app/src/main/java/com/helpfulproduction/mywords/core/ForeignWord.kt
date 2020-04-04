package com.helpfulproduction.mywords.core

class ForeignWord(
    val word: String,
    val transcription: String,
    val examples: List<Example>
) {
    var status = STATUS_DEFAULT

    companion object {
        const val STATUS_DEFAULT = 0
    }
}