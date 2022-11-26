package com.tradiebot.cythero.app.ui.user_info

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.app.ui.login.Event
import com.tradiebot.cythero.app.ui.login.LoginScreenState
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

class UserInfoScreenModel(
    val context: Context,
    val user: User,
) : StateScreenModel<UserInfoScreenState>(UserInfoScreenState.Loading) {

    init {
        updateSuccessState {
            UserInfoScreenState.Success(
                user = user,
            )
        }
    }

    private val successState: UserInfoScreenState.Success?
        get() = state.value as? UserInfoScreenState.Success

    private fun updateSuccessState(func: (UserInfoScreenState.Success) -> UserInfoScreenState.Success) {
        mutableState.update { if (it is UserInfoScreenState.Success) func(it) else it }
    }

}

sealed class UserInfoScreenState {
    @Immutable
    object Loading : UserInfoScreenState()

    @Immutable
    data class Success(
        val user: User?,
    ) : UserInfoScreenState(){
        //TODO set the auth data
    }
}