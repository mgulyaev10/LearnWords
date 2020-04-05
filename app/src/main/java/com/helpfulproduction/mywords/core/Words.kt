package com.helpfulproduction.mywords.core

import android.content.Context
import com.helpfulproduction.mywords.ThreadUtils
import com.helpfulproduction.mywords.database.WordsDatabase
import com.helpfulproduction.mywords.utils.Preference
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object Words {
    private const val DICTIONARY_ASSET_PATH = "dictionary.json"

    private lateinit var database: WordsDatabase
    private var categories: List<Category>? = null
    private var categoriesMap: HashMap<Int, String> = HashMap()

    private var words: List<Word>? = null

    fun initAsync(context: Context) {
        Observable.fromCallable {
            initDatabase(context)
        }.subscribeOn(Schedulers.io())
            .subscribe {
                categories = database.getCategories().apply {
                    downloadWordsToLearn(this)
                    this.forEach {
                        categoriesMap[it.id] = it.title
                    }
                }
            }
    }

    fun getCategories(): Observable<List<Category>> {
        return when (categories) {
            null -> Observable.fromCallable {
                         database.getCategories()
                    }.subscribeOn(Schedulers.io())
            else -> Observable.just(categories)
        }
    }

    fun getWordsByCategoryIds(ids: List<Int>): Observable<List<Word>> {
        return Observable.fromCallable{
            database.getWordsByCategoryIds(ids)
        }.subscribeOn(Schedulers.io())
    }

    fun onCategoryCheck(category: Category, isChecked: Boolean) {
        category.isSelected = isChecked
        database.onCategoryCheck(category, isChecked)
        if (isChecked) {
            getWordsByCategoryIds(listOf(category.id))
                .subscribeOn(Schedulers.io())
                .subscribe {
                    words = words?.plus(it) ?: it
                }
        } else {
            words = words?.filter { it.categoryId != category.id }
        }
    }

    fun getWords(): List<Word> {
        return words ?: emptyList()
    }

    fun categoryFromId(@Category.CategoryIds id: Int): String {
        return categoriesMap[id] ?: ""
    }

    private fun downloadWordsToLearn(categories: List<Category>) {
        ThreadUtils.assertNotMainThread()
        val ids = categories.filter { it.isSelected }.map { it.id }
        getWordsByCategoryIds(ids)
            .subscribe {
                words = it
            }
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