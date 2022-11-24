package com.tradiebot.cythero.app.ui.login

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.util.launchIO
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class LoginScreenModel(
    val context: Context,
    private val userService: UserService = Injekt.get()
) : StateScreenModel<LoginScreenState>(LoginScreenState.Loading) {
    private val successState: LoginScreenState.Success?
        get() = state.value as? LoginScreenState.Success

    fun loginUser(
        user: UserLoginUpdate
    ) {
        coroutineScope.launchIO {
            userService.loginUser(user)
        }
    }
}

sealed class Dialog {

}

sealed class LoginScreenState {
    // TODO respect Loading
    @Immutable
    object Loading : LoginScreenState()


    @Immutable
    object Success: LoginScreenState()
    /*
    @Immutable
    data class Success: LoginScreenState() {

    }

     */
}