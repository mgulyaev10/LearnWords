package com.helpfulproduction.mywords.core

import android.content.Context
import com.helpfulproduction.mywords.ThreadUtils
import com.helpfulproduction.mywords.database.WordsDatabase
import com.helpfulproduction.mywords.utils.Preference
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

object Words {
    private const val DICTIONARY_ASSET_PATH = "dictionary.json"

    private lateinit var database: WordsDatabase
    private var categories: List<Category>? = null
    private var categoriesMap: HashMap<Int, String> = HashMap()
    private var wordsUpdateListener: WordsUpdateListener? = null

    private var words: List<Word>? = null
        set(value) {
            field = value?.apply {
                wordsUpdateListener?.onWordsUpdated(this)
            }
        }

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

    fun reload(): Single<List<Word>> {
        categories?.let { categories ->
            Single.create<List<Word>> { emitter ->
                downloadWordsToLearn(categories)
                emitter.onSuccess(words)
            }.subscribeOn(Schedulers.io())
        }
        return Single.just(null)
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
        }
            .subscribeOn(Schedulers.io())
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

    fun getWords(): List<Word>? {
        return words
    }

    fun categoryFromId(@Category.CategoryIds id: Int): String {
        return categoriesMap[id] ?: ""
    }

    private fun downloadWordsToLearn(categories: List<Category>) {
        ThreadUtils.assertNotMainThread()
        val ids = categories.filter { it.isSelected }.map { it.id }
        words = shuffleWords(database.getWordsByCategoryIds(ids))
    }

    @Deprecated("DEBUG")
    fun clear() {
        database.clear()
    }

    private fun shuffleWords(words: List<Word>): List<Word> {
        ThreadUtils.assertNotMainThread()
        return words.shuffled().sortedWith(Comparator { o1, o2 -> o1?.status?.compareTo(o2?.status ?: 0) ?: -1 })
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

    fun registerWordsUpdateListener(wordsUpdateListener: WordsUpdateListener) {
        this.wordsUpdateListener = wordsUpdateListener
    }

    fun unregisterWordsUpdateListener() {
        this.wordsUpdateListener = null
    }

    interface WordsUpdateListener {
        fun onWordsUpdated(words: List<Word>)
    }

}