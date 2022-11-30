package com.tradiebot.cythero.app.ui.user_info

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.service.AnalyticsService
import com.tradiebot.cythero.domain.user.model.User
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class UserInfoScreenModel(
    val context: Context,
    val user: User,
    private val analyticsService: AnalyticsService = Injekt.get(),
) : StateScreenModel<UserInfoScreenState>(UserInfoScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                UserInfoScreenState.Success(
                    user = user,
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
        val user: User?,
    ) : UserInfoScreenState()
}