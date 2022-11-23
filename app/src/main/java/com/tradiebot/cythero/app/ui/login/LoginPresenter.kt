package com.tradiebot.cythero.app.ui.login

import android.os.Bundle
import com.tradiebot.cythero.app.ui.base.presenter.BasePresenter
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.presentation.login.LoginState
import com.tradiebot.cythero.presentation.login.LoginStateImpl
import com.tradiebot.cythero.util.launchIO
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

/**
 * Class containing user information.
 */
private data class User(val userData: String)

class LoginPresenter(
    private val state: LoginStateImpl = LoginState() as LoginStateImpl,
    private val userService: UserService = Injekt.get()
    ) : BasePresenter<LoginController>(), LoginState by state {


    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
    }

    fun loginUser(
        user: UserLoginUpdate
    ) {
        //val userService: UserService = Injekt.injectLazy<UserService>()
        presenterScope.launchIO {
            userService.loginUser(user)
            /*
           loginUser.await(
               UserLoginUpdate.testingInstance()
           )
            */
        }
    }

    /**
     * should attempt to fill user info?
     */
    fun subscribeLogin() {

    }


    sealed class Dialog {

    }
}
