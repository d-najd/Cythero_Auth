package com.tradiebot.cythero.app.ui.register

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
import com.google.gson.Gson
import com.tradiebot.cythero.app.ui.base.controller.pushController
import com.tradiebot.cythero.app.ui.user_info.UserInfoController
import com.tradiebot.cythero.app.ui.user_info.UserInfoScreenState
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.presentation.login.LoginScreen
import com.tradiebot.cythero.presentation.register.RegisterScreen
import com.tradiebot.cythero.presentation.util.LocalRouter
import kotlinx.coroutines.flow.collectLatest


object RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
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
        )
    }
}