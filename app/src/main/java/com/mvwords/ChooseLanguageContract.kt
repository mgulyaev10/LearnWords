package com.mvwords

interface ChooseLanguageContract {

    interface Presenter: BaseContract.Presenter, LanguageChooseListener

    interface View: BaseContract.View<Presenter> {
        fun isNativeLanguage(): Boolean
        fun showStudiedLanguage()
        fun openCardsFragment()
    }

}