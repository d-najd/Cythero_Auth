package com.tradiebot.cythero.app.ui.user_info

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.auth.model.Auth
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInfoScreenModel(
    val context: Context,
    val auth: Auth,
) : StateScreenModel<UserInfoScreenState>(UserInfoScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                UserInfoScreenState.Success(
                    auth = auth,
                )
            }
        }
    }
}

sealed class UserInfoScreenState {
    @Immutable
    object Loading : UserInfoScreenState()

    @Immutable
    data class Success(
        val auth: Auth,
    ) : UserInfoScreenState()
}