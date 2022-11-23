package com.tradiebot.cythero.app.ui.login

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.app.ui.base.presenter.RootController
import com.tradiebot.cythero.presentation.login.LoginScreen

class LoginController(
    bundle: Bundle? = null,
) : FullComposeController<LoginPresenter>(bundle), RootController {
    override fun createPresenter(): LoginPresenter = LoginPresenter()

    @Composable
    override fun ComposeContent() {
        val context = LocalContext.current

        LoginScreen(presenter = presenter)

        val onDismissRequest = { presenter.dialog = null }
        /*
        when (val dialog = presenter.dialog) {
            null -> {}
        }
         */
    }

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        super.onChangeStarted(handler, type)
        if (type.isEnter) {
            presenter.subscribeLogin()
        }
    }

    sealed class Dialog {

    }
}