package com.tradiebot.cythero.app.ui.register

import android.os.Bundle
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.app.ui.base.presenter.RootController

class RegisterController: FullComposeController(), RootController {

    @Composable
    override fun ComposeContent() {
        Navigator(screen = RegisterScreen)
    }

}