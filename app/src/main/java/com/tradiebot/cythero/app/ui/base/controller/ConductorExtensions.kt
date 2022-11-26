package com.tradiebot.cythero.app.ui.base.controller

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.asTransaction

@Suppress("unused")
fun Router.setRoot(controller: Controller, id: Int) {
    setRoot(controller.asTransaction().tag(id.toString()))
}

fun Router.pushController(controller: Controller) {
    pushController(controller.asTransaction())
}

@Suppress("unused")
fun Router.popControllerWithTag(tag: String): Boolean {
    val controller = getControllerWithTag(tag)
    if (controller != null) {
        popController(controller)
        return true
    }
    return false
}