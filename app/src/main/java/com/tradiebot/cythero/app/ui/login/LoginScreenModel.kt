package com.tradiebot.cythero.app.ui.login

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.Optional

class LoginScreenModel(
    val context: Context,
    private val authService: AuthService = Injekt.get()
) : StateScreenModel<LoginScreenState>(LoginScreenState.Loading) {
    private val successState: LoginScreenState.Success?
        get() = state.value as? LoginScreenState.Success

    private fun updateSuccessState(func: (LoginScreenState.Success) -> LoginScreenState.Success) {
        mutableState.update { if (it is LoginScreenState.Success) func(it) else it }
    }


    fun loginUser(
        user: UserLogin
    ) {
        coroutineScope.launchIO {
            val auth: Optional<Auth> = authService.loginUser(user)
            auth.ifPresent {
                updateSuccessState {
                    LoginScreenState.Success(
                        user = auth.get().user,
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
        val user: User?,
        //val dialog: LoginScreenModel.Dialog? = null,
    ) : LoginScreenState() {}


}