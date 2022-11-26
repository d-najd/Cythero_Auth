package com.tradiebot.cythero.app.ui.login

import android.os.Bundle
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.app.ui.base.presenter.RootController

class LoginController: FullComposeController(), RootController {

    @Composable
    override fun ComposeContent() {
        Navigator(screen = LoginScreen)
    }

}