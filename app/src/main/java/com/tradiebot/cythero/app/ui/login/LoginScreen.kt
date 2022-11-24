package com.tradiebot.cythero.app.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tradiebot.cythero.presentation.login.LoginScreen
import com.tradiebot.cythero.presentation.util.LocalRouter


class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val screenModel = rememberScreenModel { LoginScreenModel(context) }

        val state by screenModel.state.collectAsState()

        LoginScreen(
            presenter = state,
            onLoginUser = screenModel::loginUser
        )


        //val onDismissRequest = { screenModel.dismissDialog = null }
        /*
        when (val dialog = (state as? LoginScreenState.Success)?.dialog) {
            null -> {}
         */
    }

}