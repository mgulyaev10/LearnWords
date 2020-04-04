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
            val words = parseWords(it.getJSONArray("words"))
            categories.add(CategoryFullInfo(id, name, words))
        }
        return Dictionary(categories)
    }

    private fun parseWords(wordsArray: JSONArray): List<WordFullInfo> {
        val words = ArrayList<WordFullInfo>()
        wordsArray.forEachJsonObject {
            val ru = it.optString("ru")
            val engObject = it.optJSONObject(("en")) ?: throw IllegalStateException("Something wrong with JSON")
            val engWord = engObject.optString("word")
            val engTranscriprion = engObject.optString("transcription")
            // TODO: Examples
            val eng = ForeignWord(engWord, engTranscriprion, emptyList())
            words.add(WordFullInfo(ru, eng, Word.STATUS_NEW))
        }
        return words
    }

}