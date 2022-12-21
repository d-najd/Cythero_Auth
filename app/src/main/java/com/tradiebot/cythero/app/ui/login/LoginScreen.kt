package com.tradiebot.cythero.app.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bluelinelabs.conductor.asTransaction
import com.tradiebot.cythero.app.ui.base.controller.pushController
import com.tradiebot.cythero.app.ui.register.RegisterController
import com.tradiebot.cythero.app.ui.analytics.AnalyticsController
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.presentation.login.LoginScreenContent
import com.tradiebot.cythero.presentation.util.LocalRouter
import com.tradiebot.cythero.util.toast
import kotlinx.coroutines.flow.collectLatest

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        // val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val screenModel = rememberScreenModel { LoginScreenModel(context) }

        val state by screenModel.state.collectAsState()

        if (state is LoginScreenState.Loading){
            LoadingScreen()
            return
        }

        /**
         * attempt to see if the user has internet connection and if the server is connected before
         * continuing, if not return
         */

        val successState = state as LoginScreenState.Success

        LoginScreenContent(
            presenter = successState,
            onClickUserLogin = screenModel::loginUser,
            onClickRegister = { router.setRoot(RegisterController().asTransaction()) },
            onMissingFields = { screenModel.showLocalizedEvent(LoginEvent.MissingFields) },
        )

        LaunchedEffect(Unit) {
            screenModel.events.collectLatest { event ->
                when (event) {
                    is LoginEvent.UserLoggedIn -> {
                        router.pushController(AnalyticsController(event.auth))
                    }
                    is LoginEvent.LocalizedMessage -> {
                        context.toast(event.stringRes)
                    }
                }
            }
        }
    }
}