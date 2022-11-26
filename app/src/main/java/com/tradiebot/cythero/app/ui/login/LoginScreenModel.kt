package com.tradiebot.cythero.app.ui.login

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.auth.interactor.LoginUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.user.model.UserSign
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
    private val loginUser: LoginUser = Injekt.get()
) : StateScreenModel<LoginScreenState>(LoginScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                LoginScreenState.Success(
                    user = null,
                )
            }
        }
    }

    private val _events: Channel<Event> = Channel(Int.MAX_VALUE)
    val events: Flow<Event> = _events.receiveAsFlow()

    fun loginUser(
        user: UserSign
    ) {
        coroutineScope.launchIO {
            val auth: Optional<Auth> = loginUser.await(user)
            if (auth.isPresent) {
                mutableState.update {
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
    @Immutable
    object Loading : LoginScreenState()

    @Immutable
    data class Success(
        val user: Auth?,
        //val dialog: LoginScreenModel.Dialog? = null,
    ) : LoginScreenState()
}