package com.tradiebot.cythero.app.ui.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController

class LoginController: FullComposeController() {

    @Composable
    override fun ComposeContent() {
        Navigator(screen = LoginScreen)
    }

}