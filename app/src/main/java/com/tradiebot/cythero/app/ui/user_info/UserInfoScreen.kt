package com.tradiebot.cythero.app.ui.user_info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tradiebot.cythero.app.ui.base.controller.pushController
import com.tradiebot.cythero.app.ui.login.Event
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.presentation.login.LoginScreen
import com.tradiebot.cythero.presentation.user_info.UserInfoScreen
import com.tradiebot.cythero.presentation.util.LocalRouter
import kotlinx.coroutines.flow.collectLatest


data class UserInfoScreen(
    private val user: User,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val screenModel = rememberScreenModel { UserInfoScreenModel(context, user) }

        val state by screenModel.state.collectAsState()

        if (state is UserInfoScreenState.Loading){
            LoadingScreen()
            return
        }

        val successState = state as UserInfoScreenState.Success

        UserInfoScreen(
            presenter = successState,
            onBackClicked = router::popCurrentController
        )
    }
}