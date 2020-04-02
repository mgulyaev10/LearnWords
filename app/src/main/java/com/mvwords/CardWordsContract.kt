package com.mvwords

interface CardWordsContract {

    interface Presenter: BaseContract.Presenter

    interface View: BaseContract.View<Presenter>
}