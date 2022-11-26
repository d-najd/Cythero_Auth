package com.tradiebot.cythero.app.ui.register

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController

class RegisterController: FullComposeController(){

    @Composable
    override fun ComposeContent() {
        Navigator(screen = RegisterScreen)
    }

}