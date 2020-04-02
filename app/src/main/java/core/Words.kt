package core

import android.content.Context
import com.mvwords.utils.forEachJsonObject
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONArray

object Words {
    private const val DICTIONARY_ASSET_PATH = "dictionary.json"

    lateinit var dictionary: Dictionary

    fun init(context: Context) {
        Observable.fromCallable {
            val stringDictionary = readDictionaryAsset(context)
            dictionary = parseDictionary(stringDictionary)
        }.subscribeOn(Schedulers.io())
            .subscribe {}
    }

    private fun readDictionaryAsset(context: Context): String {
        context.assets
            .open(DICTIONARY_ASSET_PATH)
            .bufferedReader()
            .use {
                return it.readText()
            }
    }

    private fun parseDictionary(stringJson: String): Dictionary {
        val jsonCategories = JSONArray(stringJson)
        val categories = ArrayList<Category>()
        jsonCategories.forEachJsonObject {
            val id = it.optInt("category_id")
            val name = it.optString("category")
            val words = parseWords(it.getJSONArray("words"))
            categories.add(Category(id, name, words))
        }
        return Dictionary(categories)
    }

    private fun parseWords(wordsArray: JSONArray): List<Word> {
        val words = ArrayList<Word>()
        wordsArray.forEachJsonObject {
            val ru = it.optString("ru")
            val en = it.optString(("en"))
            words.add(Word(ru, en))
        }
        return words
    }

}