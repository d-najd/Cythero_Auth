package com.tradiebot.cythero.app.ui.login

import android.os.Bundle
import com.tradiebot.cythero.app.ui.base.presenter.BasePresenter
import com.tradiebot.cythero.presentation.login.LoginState
import com.tradiebot.cythero.presentation.login.LoginStateImpl

/**
 * Class containing user information.
 */
private data class User(val userData: String)

class LoginPresenter(
    private val state: LoginStateImpl = LoginState() as LoginStateImpl,
) : BasePresenter<LoginController>(), LoginState by state {


    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
    }


    /**
     * should attempt to fill user info?
     */
    fun subscribeLogin() {

    }


    sealed class Dialog {

    }
}
