package com.tradiebot.cythero.app.ui.login

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
import com.tradiebot.cythero.presentation.util.LocalRouter
import kotlinx.coroutines.flow.collectLatest


object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val screenModel = rememberScreenModel { LoginScreenModel(context) }

        val state by screenModel.state.collectAsState()

        if (state is LoginScreenState.Loading){
            LoadingScreen()
            return
        }

        LoginScreen(
            presenter = state,
            onLoginUser = screenModel::loginUser
        )

        LaunchedEffect(Unit) {
            screenModel.events.collectLatest { event ->
                when (event) {
                    is Event.UserLoggedIn -> {
                        router.pushController(UserInfoController(event.user))
                    }
                }
            }
        }

        //val onDismissRequest = { screenModel.dismissDialog = null }
        /*
        when (val dialog = (state as? LoginScreenState.Success)?.dialog) {
            null -> {}
         */
    }
}