package com.tradiebot.cythero.app.ui.login

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.R
import com.tradiebot.cythero.domain.auth.interactor.LoginUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.Optional

class LoginScreenModel(
    val context: Context,
    private val loginUser: LoginUser = Injekt.get(),
) : StateScreenModel<LoginScreenState>(LoginScreenState.Loading) {

    private val _events: Channel<LoginEvent> = Channel(Int.MAX_VALUE)
    val events: Flow<LoginEvent> = _events.receiveAsFlow()

    init {
        coroutineScope.launch {
            mutableState.update {
                LoginScreenState.Success(
                    user = null,
                )
            }
        }
    }

    fun loginUser(user: UserLogin) {
        coroutineScope.launchIO {
            val auth: Optional<Auth> = loginUser.await(user)
            if (auth.isPresent) {
                mutableState.update {
                    LoginScreenState.Success(
                        user = auth.get(),
                    )
                }
                _events.send(LoginEvent.UserLoggedIn(auth.get().user))
            } else {
                showLocalizedEvent(LoginEvent.NetworkError)
            }
        }
    }

    fun showLocalizedEvent(event: LoginEvent.LocalizedMessage) {
        coroutineScope.launch{
            _events.send(event)
        }
    }
}

sealed class LoginEvent {
    data class UserLoggedIn(val user: User) : LoginEvent()

    sealed class LocalizedMessage(@StringRes val stringRes: Int) : LoginEvent()
    object NetworkError: LocalizedMessage(R.string.error_network)

    //this may not belong here
    object MissingFields: LocalizedMessage(R.string.error_empty_field)
}

sealed class LoginScreenState {
    @Immutable
    object Loading : LoginScreenState()

    @Immutable
    data class Success(
        val user: Auth?,
    ) : LoginScreenState()
}