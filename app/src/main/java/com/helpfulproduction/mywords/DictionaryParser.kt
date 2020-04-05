package com.helpfulproduction.mywords

import com.helpfulproduction.mywords.core.*
import com.helpfulproduction.mywords.utils.forEachJsonObject
import org.json.JSONArray
import org.json.JSONObject

class DictionaryParser {

    fun parse(json: String): Dictionary<CategoryFullInfo> {
        val infoJson = JSONObject(json)
        val dictionaryJson = infoJson.getJSONArray("dictionary")
        val categories = ArrayList<CategoryFullInfo>()
        dictionaryJson.forEachJsonObject {
            val id = it.optInt("category_id")
            val name = it.optString("category")
            val words = parseWords(id, it.getJSONArray("words"))
            categories.add(CategoryFullInfo(id, name, false, words))
        }
        return Dictionary(categories)
    }

    private fun parseWords(categoryId: Int, wordsArray: JSONArray): List<WordFullInfo> {
        val words = ArrayList<WordFullInfo>()
        wordsArray.forEachJsonObject {
            val ru = it.optString("ru")
            val engObject = it.optJSONObject(("en")) ?: throw IllegalStateException("Something wrong with JSON")
            val engWord = engObject.optString("word")
            val engTranscriprion = engObject.optString("transcription")
            // TODO: Examples
            val eng = ForeignWord(engWord, engTranscriprion, emptyList())
            words.add(WordFullInfo(categoryId, ru, eng, Word.STATUS_NEW))
        }
        return words
    }

}