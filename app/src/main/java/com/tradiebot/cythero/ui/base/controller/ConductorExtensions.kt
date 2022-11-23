package com.tradiebot.cythero.ui.base.controller

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.asTransaction

// TODO Test if this actually sets the id
fun Router.setRoot(controller: Controller, id: Int) {
    setRoot(controller.asTransaction().tag(id.toString()))
}

fun Router.popControllerWithTag(tag: String): Boolean {
    val controller = getControllerWithTag(tag)
    if (controller != null) {
        popController(controller)
        return true
    }
    return false
}