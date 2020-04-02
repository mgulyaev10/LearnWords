package com.mvwords

class ChooseLanguagePresenter(
    private val view: ChooseLanguageContract.View
): ChooseLanguageContract.Presenter, LanguageChooseListener {

    override fun onLanguageSelect(language: Language) {
        val isNative = view.isNativeLanguage()
        Preference.setLanguage(view.getContext(), language, isNative)
        if (isNative) {
            view.showStudiedLanguage()
        } else {
            view.openCardsFragment()
        }
    }

}