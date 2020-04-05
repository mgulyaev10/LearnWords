package com.helpfulproduction.mywords.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.helpfulproduction.mywords.AppContextHolder
import com.helpfulproduction.mywords.utils.Preference

class DatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        createQueries.forEach {
            db?.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Preference.setDataUnpacked(AppContextHolder.context, isDataUnpacked = false)
        deleteQueries.forEach {
            db?.execSQL(it)
        }
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun clear(database: SQLiteDatabase) {
        onUpgrade(database, VERSION, VERSION)
    }

    private companion object {
        const val VERSION = 1
        const val NAME = "MyWords.db"

        const val SQL_CREATE_CATEGORIES_ENTRIES =
            "CREATE TABLE ${WordsDatabaseContract.CategoriesEntry.TABLE_NAME} (" +
                    "${WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_ID} INTEGER PRIMARY KEY," +
                    "${WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_CATEGORY_NAME} TEXT," +
                    "${WordsDatabaseContract.CategoriesEntry.COLUMN_NAME_SELECTED} INTEGER)"

        const val SQL_CREATE_WORDS_ENTRIES =
            "CREATE TABLE ${WordsDatabaseContract.WordsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${WordsDatabaseContract.WordsEntry.COLUMN_NAME_CATEGORY_ID} INTEGER," +
                    "${WordsDatabaseContract.WordsEntry.COLUMN_NAME_RU} TEXT," +
                    "${WordsDatabaseContract.WordsEntry.COLUMN_NAME_ENG} TEXT," +
                    "${WordsDatabaseContract.WordsEntry.COLUMN_NAME_TRANSCRIPTION} TEXT," +
                    "${WordsDatabaseContract.WordsEntry.COLUMN_NAME_STATUS} STATUS)"

        const val SQL_CREATE_EXAMPLES_ENTRIES =
            "CREATE TABLE ${WordsDatabaseContract.EnglishExamplesEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${WordsDatabaseContract.EnglishExamplesEntry.COLUMN_NAME_WORD} TEXT," +
                    "${WordsDatabaseContract.EnglishExamplesEntry.COLUMN_NAME_PHRASE} TEXT," +
                    "${WordsDatabaseContract.EnglishExamplesEntry.COLUMN_NAME_RUS_PHRASE} TEXT)"

        val createQueries = listOf(
            SQL_CREATE_CATEGORIES_ENTRIES,
            SQL_CREATE_WORDS_ENTRIES,
            SQL_CREATE_EXAMPLES_ENTRIES
        )

        const val SQL_DELETE_CATEGORIES_ENTRIES = "DROP TABLE IF EXISTS ${WordsDatabaseContract.CategoriesEntry.TABLE_NAME}"
        const val SQL_DELETE_WORDS_ENTRIES = "DROP TABLE IF EXISTS ${WordsDatabaseContract.WordsEntry.TABLE_NAME}"
        const val SQL_DELETE_ENG_WORDS_ENTRIES = "DROP TABLE IF EXISTS english"
        const val SQL_DELETE_ENG_EXAMPLES_ENTRIES = "DROP TABLE IF EXISTS ${WordsDatabaseContract.EnglishExamplesEntry.TABLE_NAME}"

        val deleteQueries = listOf(
            SQL_DELETE_CATEGORIES_ENTRIES,
            SQL_DELETE_WORDS_ENTRIES,
            SQL_DELETE_ENG_WORDS_ENTRIES,
            SQL_DELETE_ENG_EXAMPLES_ENTRIES
        )
    }
}