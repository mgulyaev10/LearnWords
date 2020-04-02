package com.mvwords.utils

import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.forEachJsonObject(action: (JSONObject) -> Unit) {
    for (i in 0 until length()) {
        action.invoke(getJSONObject(i))
    }
}