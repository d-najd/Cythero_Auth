package com.tradiebot.cythero.app.ui.register

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.user.model.User
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterScreenViewModel(
    val context: Context,
) : StateScreenModel<RegisterScreenState>(RegisterScreenState.Loading) {
    init {
        coroutineScope.launch {
            mutableState.update {
                RegisterScreenState.Success(
                    user = null,
                )
            }
        }
    }
}

sealed class RegisterScreenState {
    @Immutable
    object Loading : RegisterScreenState()

    @Immutable
    data class Success(
        val user: User?,
    ) : RegisterScreenState()
}