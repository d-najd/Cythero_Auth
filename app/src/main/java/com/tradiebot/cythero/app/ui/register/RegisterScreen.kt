package com.tradiebot.cythero.app.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bluelinelabs.conductor.asTransaction
import com.tradiebot.cythero.app.ui.login.LoginController
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.presentation.register.RegisterScreen
import com.tradiebot.cythero.presentation.util.LocalRouter
import com.tradiebot.cythero.util.toast
import kotlinx.coroutines.flow.collectLatest


object RegisterScreen : Screen {
    @Composable
    override fun Content() {
        // val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val screenModel = rememberScreenModel { RegisterScreenViewModel(context) }

        val state by screenModel.state.collectAsState()

        if (state is RegisterScreenState.Loading){
            LoadingScreen()
            return
        }

        val successState = state as RegisterScreenState.Success

        RegisterScreen(
            presenter = successState,
            onClickUserRegister = screenModel::registerUser,
            onClickLogin = { router.setRoot(LoginController().asTransaction()) },
        )

        LaunchedEffect(Unit) {
            screenModel.events.collectLatest { event ->
                when (event) {
                    is Event.UserRegistered -> {
                        context.toast("User Successfully registered")
                        router.setRoot(LoginController().asTransaction())
                    }
                    is Event.NetworkError -> {
                        context.toast("Network Error")
                    }
                }
            }
        }

    }
}