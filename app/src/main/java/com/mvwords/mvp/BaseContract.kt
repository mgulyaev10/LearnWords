package com.mvwords.mvp

import android.content.Context

interface BaseContract {

    interface Presenter

    interface View<P: Presenter> {
        var presenter: P?

        fun getContext(): Context?
    }

}