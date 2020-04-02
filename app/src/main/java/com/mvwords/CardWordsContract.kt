package com.mvwords

import com.mvwords.mvp.BaseContract

interface CardWordsContract {

    interface Presenter: BaseContract.Presenter

    interface View: BaseContract.View<Presenter>
}