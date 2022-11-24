package com.tradiebot.cythero.app.ui.login

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class LoginScreenModel(
    val context: Context,
    private val userService: UserService = Injekt.get()
) : StateScreenModel<LoginScreenState>(LoginScreenState.Loading) {
    private val successState: LoginScreenState.Success?
        get() = state.value as? LoginScreenState.Success

    private fun updateSuccessState(func: (LoginScreenState.Success) -> LoginScreenState.Success) {
        mutableState.update { if (it is LoginScreenState.Success) func(it) else it }
    }


    fun loginUser(
        user: UserLoginUpdate
    ) {
        coroutineScope.launchIO {
            val userLogged: UserComplete? = userService.loginUser(user)

            if(userLogged != null) {
                updateSuccessState {
                    LoginScreenState.Success(
                        user = userLogged,
                    )
                }
            }

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
    data class Success(
        val user: UserComplete?,
        //val dialog: LoginScreenModel.Dialog? = null,
    ) : LoginScreenState() {}


}