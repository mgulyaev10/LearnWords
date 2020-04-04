package com.helpfulproduction.mywords.database

import android.provider.BaseColumns
import com.helpfulproduction.mywords.core.Category

object WordsDatabaseContract {

    object CategoriesEntry: BaseColumns {
        const val TABLE_NAME = "categories"
        const val COLUMN_NAME_CATEGORY_ID = "category_id"
        const val COLUMN_NAME_CATEGORY_NAME = "category_name"
    }

    object WordsEntry: BaseColumns {
        const val TABLE_NAME = "words"
        const val COLUMN_NAME_CATEGORY_ID = "category_id"
        const val COLUMN_NAME_RU = "rus"
        const val COLUMN_NAME_ENG = "word"
        const val COLUMN_NAME_TRANSCRIPTION = "transcription"
        const val COLUMN_NAME_STATUS = "status"
    }

    object EnglishExamplesEntry: BaseColumns {
        const val TABLE_NAME = "examples"
        const val COLUMN_NAME_WORD = "word"
        const val COLUMN_NAME_RUS_PHRASE = "rus"
        const val COLUMN_NAME_PHRASE = "phrase"
    }

    data class DatabaseCategory(
        @Category.CategoryIds val id: Int,
        val name: String
    )

    data class DatabaseWord(
        @Category.CategoryIds val categoryId: Int,
        val ru: String,
        val eng: String,
        val transcription: String
    )

    data class DatabaseEnglishExample(
        val word: String,
        val rusPhrase: String,
        val phrase: String
    )


}