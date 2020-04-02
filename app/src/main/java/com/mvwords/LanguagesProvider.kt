package com.mvwords

class LanguagesProvider {

    val languages = listOf(
        Language(Language.RUSSIA, R.string.russian, R.drawable.ic_russia_64),
        Language(Language.ENGLISH, R.string.english, R.drawable.ic_united_kingdom_64),
        Language(Language.GERMAN, R.string.german, R.drawable.ic_germany_64),
        Language(Language.SPAIN, R.string.spanish, R.drawable.ic_spain_64),
        Language(Language.ITALY, R.string.italian, R.drawable.ic_italy_64),
        Language(Language.FRANCE, R.string.french, R.drawable.ic_france_64)
    )

    fun getCount(): Int = languages.size

}