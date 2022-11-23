package com.tradiebot.cythero.app.ui.base.controller

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.tradiebot.cythero.app.ui.base.presenter.NucleusConductorDelegate
import com.tradiebot.cythero.app.ui.base.presenter.NucleusConductorLifecycleListener
import nucleus.factory.PresenterFactory
import nucleus.presenter.Presenter

@Suppress("LeakingThis")
abstract class NucleusController<VB : ViewBinding, P : Presenter<*>>(val bundle: Bundle? = null) :
    BaseController<VB>(bundle),
    PresenterFactory<P> {

    private val delegate = NucleusConductorDelegate(this)

    val presenter: P
        get() = delegate.presenter!!

    init {

        addLifecycleListener(NucleusConductorLifecycleListener(delegate))
    }
}
