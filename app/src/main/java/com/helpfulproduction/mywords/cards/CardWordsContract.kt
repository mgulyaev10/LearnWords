package com.helpfulproduction.mywords.cards

import com.helpfulproduction.mywords.mvp.BaseContract

interface CardWordsContract {

    interface Presenter: BaseContract.Presenter

    interface View: BaseContract.View<Presenter>
}