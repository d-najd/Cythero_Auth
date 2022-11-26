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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.Optional

class LoginScreenModel(
    val context: Context,
    private val authService: AuthService = Injekt.get()
) : StateScreenModel<LoginScreenState>(LoginScreenState.Loading) {

    init {
        updateSuccessState {
            LoginScreenState.Success(
                user = null,
            )
        }
    }

    private val _events: Channel<Event> = Channel(Int.MAX_VALUE)
    val events: Flow<Event> = _events.receiveAsFlow()

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
            if(auth.isPresent){
                updateSuccessState {
                    LoginScreenState.Success(
                        user = auth.get(),
                    )
                }
                _events.send(Event.UserLoggedIn(auth.get().user))
            }
        }
    }
}

sealed class Dialog { }

sealed class Event {
    data class UserLoggedIn(val user: User) : Event()
}

sealed class LoginScreenState {
    // TODO respect Loading
    @Immutable
    object Loading : LoginScreenState()


    @Immutable
    data class Success(
        val user: Auth?,
        //val dialog: LoginScreenModel.Dialog? = null,
    ) : LoginScreenState() {
        //TODO set the auth data
    }
}