package com.mvwords

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseMvpFragment<P: BaseContract.Presenter>: Fragment(), BaseContract.View<P> {

    override var presenter: P? = null

    override fun getContext(): Context? = activity

}