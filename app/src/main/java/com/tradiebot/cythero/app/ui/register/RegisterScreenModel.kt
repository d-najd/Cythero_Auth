package com.tradiebot.cythero.app.ui.register

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.R
import com.tradiebot.cythero.domain.auth.interactor.RegisterUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.user.model.UserRegister
import com.tradiebot.cythero.presentation.util.CytheroStateScreenModel
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class RegisterStateScreenViewModel(
    val context: Context,
    private val registerUser: RegisterUser = Injekt.get(),
) : CytheroStateScreenModel<RegisterScreenState>(context, RegisterScreenState.Loading) {

    private val _events: Channel<RegisterEvent> = Channel(Int.MAX_VALUE)
    val events: Flow<RegisterEvent> = _events.receiveAsFlow()

    init {
        coroutineScope.launch {
            mutableState.update {
                RegisterScreenState.Success(
                    user = null,
                )
            }
        }
    }

    fun registerUser(user: UserRegister) {
        coroutineScope.launchIO {
            val auth = registerUser.await(user)
            if (auth != null) {
                mutableState.update {
                    RegisterScreenState.Success(
                        user = auth
                    )
                }
                _events.send(RegisterEvent.UserRegistered(auth.user))
            }
        }
}
    fun showLocalizedEvent(event: RegisterEvent.LocalizedMessage) {
        coroutineScope.launch{
            _events.send(event)
        }
    }
}

sealed class RegisterEvent {
    data class UserRegistered(val user: User): RegisterEvent()
    
    sealed class LocalizedMessage(@StringRes val stringRes: Int) : RegisterEvent()
    object MissingFields: LocalizedMessage(R.string.error_empty_field)
    object NotMatchingPassword: LocalizedMessage(R.string.error_password_do_not_match)
}

sealed class RegisterScreenState {
    @Immutable
    object Loading : RegisterScreenState()

    @Immutable
    data class Success(
        val user: Auth?,
    ) : RegisterScreenState()
}