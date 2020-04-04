package com.helpfulproduction.mywords.core

import android.content.Context
import com.helpfulproduction.mywords.database.WordsDatabase
import com.helpfulproduction.mywords.utils.Preference
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object Words {
    private const val DICTIONARY_ASSET_PATH = "dictionary.json"

    private lateinit var database: WordsDatabase

    fun initAsync(context: Context) {
        Observable.fromCallable {
            initDatabase(context)
        }
            .subscribeOn(Schedulers.io())
            .subscribe {}
    }

    fun getCategories(): Observable<List<Category>> {
        return Observable.fromCallable{database.getCategories()}
            .subscribeOn(Schedulers.io())
    }

    fun getWordsByCategoryId(id: Int): Observable<List<Word>> {
        return Observable.fromCallable{
            database.getWordsByCategoryId(id)
        }.subscribeOn(Schedulers.io())
    }

    @Deprecated("DEBUG")
    fun clear() {
        database.clear()
    }

    private fun initDatabase(context: Context) {
        database = WordsDatabase(context)
        fillDatabaseIfNeed(context)
    }

    private fun fillDatabaseIfNeed(context: Context) {
        val isDataExists = Preference.isDataUnpacked(context)
        if (isDataExists) {
            return
        } else {
            database.fillDatabase(readDictionaryAsset(context))
//            clear()
        }
    }

    private fun readDictionaryAsset(context: Context): String {
        context.assets
            .open(DICTIONARY_ASSET_PATH)
            .bufferedReader()
            .use {
                return it.readText()
            }
    }

}