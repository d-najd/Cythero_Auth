package com.tradiebot.cythero.presentation.login

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tradiebot.cythero.app.ui.login.LoginPresenter

@Stable
interface LoginState {
    var dialog: LoginPresenter.Dialog?
}

fun LoginState(): LoginState {
    return LoginStateImpl()
}

class LoginStateImpl : LoginState {
    override var dialog: LoginPresenter.Dialog? by mutableStateOf(null)
}
