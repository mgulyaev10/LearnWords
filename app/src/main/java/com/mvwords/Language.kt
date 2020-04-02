package com.mvwords

import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.annotation.StringRes

data class Language(
    @LanguageIds val id: Int,
    @StringRes val titleRes: Int,
    @DrawableRes val flagRes: Int
) {

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(DEFAULT, RUSSIA, ENGLISH, GERMAN, SPAIN, ITALY, FRANCE)
    annotation class LanguageIds

    companion object {
        const val DEFAULT = -1
        const val RUSSIA = 0
        const val ENGLISH = 1
        const val GERMAN = 2
        const val SPAIN = 3
        const val ITALY = 4
        const val FRANCE = 5
    }

}