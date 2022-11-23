package com.tradiebot.cythero.app.ui.base.presenter

import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import nucleus.presenter.RxPresenter
import rx.Observable

open class BasePresenter<V> : RxPresenter<V>() {

    var presenterScope: CoroutineScope = MainScope()

    override fun onDestroy() {
        super.onDestroy()
        presenterScope.cancel()
    }

    // We're trying to avoid using Rx, so we "undeprecate" this
    @Suppress("DEPRECATION")
    override fun getView(): V? {
        return super.getView()
    }
}
