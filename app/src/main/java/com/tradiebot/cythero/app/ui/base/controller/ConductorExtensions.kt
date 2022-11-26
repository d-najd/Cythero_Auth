package com.tradiebot.cythero.app.ui.base.controller

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.asTransaction
import com.tradiebot.cythero.app.ui.base.Screens

// TODO Test if this actually sets the id
fun Router.setRoot(controller: Controller, id: Int) {
    setRoot(controller.asTransaction().tag(id.toString()))
}

fun Router.setRoot(controller: Controller, screen: Screens) {
    setRoot(controller.asTransaction().tag(screen.toString()))
}

fun Router.pushController(controller: Controller) {
    pushController(controller.withFadeTransaction())
}

fun Controller.withFadeTransaction(): RouterTransaction {
    return RouterTransaction.with(this)
        .pushChangeHandler(OneWayFadeChangeHandler())
        .popChangeHandler(OneWayFadeChangeHandler())
}

fun Router.popControllerWithTag(tag: String): Boolean {
    val controller = getControllerWithTag(tag)
    if (controller != null) {
        popController(controller)
        return true
    }
    return false
}