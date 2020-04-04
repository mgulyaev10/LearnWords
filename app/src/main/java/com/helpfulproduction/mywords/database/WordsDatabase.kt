package com.helpfulproduction.mywords.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.WorkerThread
import com.helpfulproduction.mywords.AppContextHolder
import com.helpfulproduction.mywords.DictionaryParser
import com.helpfulproduction.mywords.ThreadUtils
import com.helpfulproduction.mywords.core.Category
import com.helpfulproduction.mywords.core.ForeignWord.Companion.STATUS_DEFAULT
import com.helpfulproduction.mywords.core.Word
import com.helpfulproduction.mywords.utils.Preference

class WordsDatabase(context: Context) {

    private val databaseOpenHelper = DatabaseOpenHelper(context)
    private val database: SQLiteDatabase = databaseOpenHelper.writableDatabase

    @WorkerThread
    fun getCategories(): List<Category> {
        ThreadUtils.assertNotMainThread()

        val cursor = database.query(
            WordsDatabaseContract.CategoriesEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_NAME
        )

        val categories = arrayListOf<Category>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_NAME))
            categories.add(Category(id, name))
        }
        cursor.close()
        return categories
    }

    @WorkerThread
    fun getWordsByCategoryId(id: Int): List<Word> {
        ThreadUtils.assertNotMainThread()

        val cursor = database.query(
            WordsDatabaseContract.WordsEntry.TABLE_NAME,
            null,
            "category_id=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        val words = arrayListOf<Word>()
        while (cursor.moveToNext()) {
            val rus = cursor.getString(cursor.getColumnIndexOrThrow(WordsDatabaseContract.WordsEntry.COLUMN_NAME_RU))
            val eng = cursor.getString(cursor.getColumnIndexOrThrow(WordsDatabaseContract.WordsEntry.COLUMN_NAME_ENG))
            val transcription = cursor.getString(cursor.getColumnIndexOrThrow(WordsDatabaseContract.WordsEntry.COLUMN_NAME_TRANSCRIPTION))
            val status = cursor.getInt(cursor.getColumnIndexOrThrow(WordsDatabaseContract.WordsEntry.COLUMN_NAME_STATUS))
            words.add(Word(rus, eng, transcription, status))
        }
        cursor.close()
        return words
    }

    @WorkerThread
    fun fillDatabase(jsonInfo: String) {
        clear()
        val dictionary = DictionaryParser().parse(jsonInfo)
        val databaseCategories = ArrayList<WordsDatabaseContract.DatabaseCategory>()
        val databaseWords = ArrayList<WordsDatabaseContract.DatabaseWord>()
        val databaseEnglishExamples = ArrayList<WordsDatabaseContract.DatabaseEnglishExample>()
        dictionary.categories.forEach { category ->
            val categoryId = category.id
            val categoryName = category.title
            databaseCategories.add(WordsDatabaseContract.DatabaseCategory(categoryId, categoryName))

            category.words.forEach { word ->
                val russianWord = word.russian
                val englishWordWrapper = word.foreignWord
                val englishWord = englishWordWrapper.word
                val transcription = englishWordWrapper.transcription

                databaseWords.add(WordsDatabaseContract.DatabaseWord(categoryId, russianWord, englishWord, transcription))

                englishWordWrapper.examples.forEach { example ->
                    databaseEnglishExamples.add(WordsDatabaseContract.DatabaseEnglishExample(englishWord, example.ruPhrase, example.foreignPhrase))
                }
            }
        }

        fillCategories(databaseCategories)
        fillWords(databaseWords)
        fillEnglishExamples(databaseEnglishExamples)
        Preference.setDataUnpacked(AppContextHolder.context, isDataUnpacked = true)
    }

    @WorkerThread
    fun clear() {
        databaseOpenHelper.clear(database)
    }

    private fun fillCategories(categories: List<WordsDatabaseContract.DatabaseCategory>) {
        categories.forEach { category ->
            val value = ContentValues().apply {
                put(WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_ID, category.id)
                put(WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_NAME, category.name)
            }

            database.insert(WordsDatabaseContract.CategoriesEntry.TABLE_NAME, null, value)
        }
    }

    private fun fillWords(words: List<WordsDatabaseContract.DatabaseWord>) {
        words.forEach { word ->
            val value = ContentValues().apply {
                put(WordsDatabaseContract.WordsEntry.COLUMN_NAME_CATEGORY_ID, word.categoryId)
                put(WordsDatabaseContract.WordsEntry.COLUMN_NAME_RU, word.ru)
                put(WordsDatabaseContract.WordsEntry.COLUMN_NAME_ENG, word.eng)
                put(WordsDatabaseContract.WordsEntry.COLUMN_NAME_TRANSCRIPTION, word.transcription)
                put(WordsDatabaseContract.WordsEntry.COLUMN_NAME_STATUS, STATUS_DEFAULT)
            }

            database.insert(WordsDatabaseContract.WordsEntry.TABLE_NAME, null, value)
        }
    }

    private fun fillEnglishExamples(examples: List<WordsDatabaseContract.DatabaseEnglishExample>) {
        examples.forEach { example ->
            val value = ContentValues().apply {
                put(WordsDatabaseContract.EnglishExamplesEntry.COLUMN_NAME_WORD, example.word)
                put(WordsDatabaseContract.EnglishExamplesEntry.COLUMN_NAME_RUS_PHRASE, example.rusPhrase)
                put(WordsDatabaseContract.EnglishExamplesEntry.COLUMN_NAME_PHRASE, example.phrase)
            }

            database.insert(WordsDatabaseContract.EnglishExamplesEntry.TABLE_NAME, null, value)
        }
    }

}