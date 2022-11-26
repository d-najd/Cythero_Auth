package com.tradiebot.cythero.app.ui.register

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.auth.interactor.RegisterUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.user.model.UserRegister
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.*

class RegisterScreenViewModel(
    val context: Context,
    private val registerUser: RegisterUser = Injekt.get()
) : StateScreenModel<RegisterScreenState>(RegisterScreenState.Loading) {

    private val _events: Channel<Event> = Channel(Int.MAX_VALUE)
    val events: Flow<Event> = _events.receiveAsFlow()

    init {
        coroutineScope.launch {
            mutableState.update {
                RegisterScreenState.Success(
                    user = null,
                )
            }
        }
    }

    fun registerUser(
        user: UserRegister
    ) {
        coroutineScope.launchIO {
            val auth: Optional<Auth> = registerUser.await(user)
            if (auth.isPresent) {
                mutableState.update {
                    RegisterScreenState.Success(
                        user = auth.get()
                    )
                }
                _events.send(Event.UserRegistered(auth.get().user))
            } else {
                _events.send(Event.NetworkError)
            }
        }
    }
}

sealed class Event {
    data class UserRegistered(val user: User): Event()
    object NetworkError: Event()
}

sealed class RegisterScreenState {
    @Immutable
    object Loading : RegisterScreenState()

    @Immutable
    data class Success(
        val user: Auth?,
    ) : RegisterScreenState()
}